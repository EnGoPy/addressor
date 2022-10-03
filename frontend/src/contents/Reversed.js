import React, {useEffect, useState} from "react";
import TagListItem from "./TagListItem";
import CreateTagForm from "./CreateTagForm";
import RemoveTagForm from "./RemoveTagForm";
import config from "../config.json";

const Reversed = () => {

    const [reversedTags, setReversedTags] = useState();
    const [fetchedData, setFetchedData] = useState(false);
    const [validTags, setValidTags] = useState();

    useEffect(() => {
        fetch("http://localhost:2300/reverse/tags")
            .then(res => res.json()
            )
            .then(json => {
                    setReversedTags(json)
                    // setFetchedData(true)
                    setValidTags(true)
                }
            )
            .catch(() => {
                setValidTags(false);
                console.log("Unable to fetch data from API")
            })
    }, [validTags]);

    const refreshTagList = () => {
        setValidTags(false);
    }

    return (
        <>
            <h2>Allowed tag pairs for reversed geocoding</h2>
            {validTags ? <TagListItem tags={reversedTags}/> : `Waiting for initialisation`}
            <CreateTagForm callback={() => refreshTagList()} urlSufix={config.reversedTagUrl}/>
            <RemoveTagForm callback={() => refreshTagList()} urlSufix={config.reversedTagUrl}/>
        </>
    )
}

export default Reversed;