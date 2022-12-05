import React, {useEffect, useState} from "react";
import TagListItem from "./TagListItem";
import CreateTagForm from "./CreateTagForm";
import TryAutoSearch from "./TryAutoSearch";
import config from "../config.json";


const Autosearch = () => {

    const [autoSearchTags, setAutoSearchTags] = useState();
    const [uiRefreshTrigger, setUiRefreshTrigger] = useState(false)
    const [validTags, setValidTags] = useState();

    useEffect(() => {
        const controller = new AbortController();
        const signal = controller.signal;
        fetch("http://localhost:2300/autosearch/tags", {signal})
            .then(res => res.json()
            )
            .then(json => {
                    if (!signal.aborted) {
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
        setUiRefreshTrigger((prev) => !prev);
    }

    return (
        <>
            <div className="row">
                <div className="col-5">
                    <h3>Allowed tag pairs for autosearch</h3>
                    {validTags ? <TagListItem tags={autoSearchTags} refreshCallback={() => refreshTagList()}
                                              removeUrl={config.autoSearchTagUrl}/> : `Waiting for initialisation`}
                </div>
                <div className="col-7">
                    <CreateTagForm callback={() => refreshTagList()} urlSufix={config.autoSearchTagUrl}/>
                    <TryAutoSearch/>
                </div>
            </div>



        </>
    )
}

export default Autosearch;