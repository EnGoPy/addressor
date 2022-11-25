import React, {useEffect, useState} from "react";
import TagListItem from "./TagListItem";
import CreateTagForm from "./CreateTagForm";
import config from "../config.json";


const Autosearch = () => {

    const [autoSearchTags, setAutoSearchTags] = useState();
    const [uiRefreshTrigger, setUiRefreshTrigger] = useState(false)
    const [validTags, setValidTags] = useState();

    useEffect(() => {
        const controller = new AbortController();
        const signal = controller.signal;
        console.log(`useEffect Called!!!!! with uiRefreshTrigger:${uiRefreshTrigger}`)
        fetch("http://localhost:2300/autosearch/tags", {signal})
            .then(res => res.json()
            )
            .then(json => {
                    if (!signal.aborted) {
                        console.log("Have data! setting tags!")
                        setAutoSearchTags((prev) => (json));
                        setValidTags(true);
                    }
                }
            )
            .catch(() => {
                console.log("Unable to fetch data from API")
            })
        return () => {
            setValidTags(false);
        }
    }, [uiRefreshTrigger]);


    const refreshTagList = () => {
        console.log("refreshTagList start")
        setUiRefreshTrigger((prev) => !prev);
        console.log("refreshTagList END")
    }

    return (
        <>
            <h2>Allowed tag pairs for autosearch</h2>
            {validTags ? <TagListItem tags={autoSearchTags} refreshCallback={() => refreshTagList()}
                                      removeUrl={config.autoSearchTagUrl}/> : `Waiting for initialisation`}
            <CreateTagForm callback={() => refreshTagList()} urlSufix={config.autoSearchTagUrl}/>
        </>
    )
}

export default Autosearch;