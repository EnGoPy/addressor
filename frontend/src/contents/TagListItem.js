import React from "react";

const TagListItem = (props) => {
    const {tags} = props;
    console.log(Object.entries(tags))
    let renderedMap = [];
    for (let [key, value] of Object.entries(tags)) {
        let values = [...value].sort().join(", ")
        renderedMap.push(
            <div key={`${key}+${values}`} className="row py-2">
                <div className="col-2">{key}</div>
                <div className="col">{values}</div>
            </div>)
    }

    return (
        <>
            <div>{renderedMap}</div>
        </>
    )
}

export default TagListItem;