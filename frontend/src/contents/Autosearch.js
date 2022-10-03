import React, {useEffect, useState} from "react";
import TagListItem from "./TagListItem";
import CreateTagForm from "./CreateTagForm";
import config from "../config.json";
import RemoveTagForm from "./RemoveTagForm";


const Autosearch = () => {

    const [autoSearchTags, setAutoSearchTags] = useState();
    const [fetchedData, setFetchedData] = useState(false);
    const [validTags, setValidTags] = useState();

    useEffect(() => {
        fetch("http://localhost:2300/autosearch/tags")
            .then(res => res.json()
            )
            .then(json => {
                    setAutoSearchTags(json)
                    setFetchedData(true)
                    setValidTags(true)
                }
            )
            .catch(() => {
                setFetchedData(false);
                console.log("Unable to fetch data from API")
            })
    }, [validTags]);

    const refreshTagList = () => {
        setValidTags(false);
    }

    return (
        <>
            <h2>Allowed tag pairs for autosearch</h2>
            {validTags ? <TagListItem tags={autoSearchTags}/> : `Waiting for initialisation`}
            <CreateTagForm callback={() => refreshTagList()} urlSufix={config.autoSearchTagUrl}/>
            <RemoveTagForm callback={() => refreshTagList()} urlSufix={config.autoSearchTagUrl}/>
        </>
    )
}

export default Autosearch;