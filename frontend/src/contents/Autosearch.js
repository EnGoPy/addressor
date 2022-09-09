import React, {useEffect, useState} from "react";
import AutoSearchListItem from "../components/AutoSearchListItem";
import {Button, Table} from "reactstrap";

const Autosearch = () => {

    const [autoSearchTags, setAutoSearchTags] = useState();
    const [fetchedData, setFetchedData] = useState(false);

    useEffect(() => {
        fetch("http://localhost:2300/autosearch/tags")
            .then(res =>  res.json()
            )
            .then(json => {
                setAutoSearchTags(json)
                setFetchedData(true)
                }
            )
            .catch(() => {
                setFetchedData(false);
                console.log("Unable to fetch data from API")
            })
    }, []);

    return (
        <>
            <h2>Allowed tag pairs for autosearch</h2>
            {fetchedData ? <AutoSearchListItem tags={autoSearchTags}/> : `Waiting for initialisation`}
        </>
    )
}

export default Autosearch;