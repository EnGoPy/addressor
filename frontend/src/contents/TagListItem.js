import React from "react";
import config from "../config.json";
import {ReactComponent as InfoIcon} from "../icons/info-circle-fill.svg"
import TagApi from "../model/TagApi";

const TagListItem = (props) => {
    const {tags, refreshCallback, removeUrl} = props;

    let renderedMap = [];
    let tagMap = new Map(Object.entries(tags))

    function handleRemove(k, v){
        console.log("HandleRemove start");

        if (k.length !== 0 && v.length !== 0) {
            let tagApi = new TagApi(k, v);
            const requestOptions = {
                method: `DELETE`,
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(tagApi)
            };
            console.debug('Sending request : ' + requestOptions)
            fetch(config["backend-url"] + removeUrl, requestOptions)
                .then(json => {
                    refreshCallback();
                })
                .catch(() => {
                    console.debug("Error sending DELETE request")
                });
            console.log("HandleRemove end");
        }
    }

    tagMap.forEach((values, key) => {
        let parsedValuesWithAddons = values.map(singleValue => {
            let wikiBaseUrl = ((String)(config.OSM_wikiTagAddress)).replace("{key}", key).replace("{value}", singleValue);
            // console.log(wikiBaseUrl);

            return (
                <div key={key+singleValue} className="row border border-secondary">
                    <div className="col">
                        {singleValue}
                    </div>
                    <div className="col">
                        <a href={wikiBaseUrl} title="OSM wiki">
                            <InfoIcon className="ui-priority-primary"/>
                        </a>
                    </div>
                    <div className="col">
                        <button  type="button" className="btn btn-danger btn-sm" onClick={() => handleRemove(key, singleValue)}  title="Remove">
                            Remove
                        </button>
                    </div>
                </div>
            )
        });

        renderedMap.push(
                <div key={key} className="row border border-primary">
                    <div className="col-3 font-weight-bold">{key}</div>
                    <div className="col">{parsedValuesWithAddons}</div>
                </div>
        )
    })

    return (
        <>
            {renderedMap}
        </>
    )
}

export default TagListItem;