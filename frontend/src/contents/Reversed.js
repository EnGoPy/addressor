import React, {useEffect, useState} from "react";
import TagListItem from "./TagListItem";
import CreateTagForm from "./CreateTagForm";
import RemoveTagForm from "./RemoveTagForm";
import config from "../config.json";

const Reversed = () => {

    const [reversedTags, setReversedTags] = useState();
    const [validTags, setValidTags] = useState();
    const [uiRefreshTrigger, setUiRefreshTrigger] = useState(false)

    useEffect(() => {
        const controller = new AbortController();
        const signal = controller.signal;
        fetch("http://localhost:2300/reverse/tags", {signal})
            .then(res => res.json()
            )
            .then(json => {
                    if (!signal.aborted) {
                        setReversedTags(json)
                        setValidTags(true)
                    }
                }
            )
            .catch(() => {
                setValidTags(false);
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
            <h2>Allowed tag pairs for reversed geocoding</h2>
            {validTags ? <TagListItem tags={reversedTags} refreshCallback={() => refreshTagList()}
                                      removeUrl={config.reversedTagUrl}/> : `Waiting for initialisation`}
            <CreateTagForm callback={() => refreshTagList()} urlSufix={config.reversedTagUrl}/>
        </>
    )
}

export default Reversed;