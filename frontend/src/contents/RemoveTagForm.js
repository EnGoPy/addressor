import React, {useState} from "react";
import config from "../config.json";
import "./AutosearchForm.js.css"
import TagApi from "../model/TagApi";

const RemoveTagForm = (props) => {

    const [tag, setTag] = useState();
    const [key, setKey] = useState();
    let requestMessage = "";

    const refreshCallback = () => {
        console.log("Callback called!")
        props.callback();
    }

    const handleChangeTag = tagChange => {
        setTag(tagChange.target.value)
    }

    const handleChangeKey = (keyChange) => {
        setKey(keyChange.target.value)
    }

    const handleSubmit = (e) => {
        console.log(tag)
        console.log(key)
        e.preventDefault();


        if (tag.length !== 0 && key.length !== 0) {
            let tagApi = new TagApi(tag, key);
            const requestOptions = {
                method: `DELETE`,
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(tagApi)
            };
            console.log('Doing a request : ' + requestOptions)
            fetch(config["backend-url"] + props.urlSufix,
                requestOptions)
                .then(() => {
                    setTag('');
                    setKey('');
                })
                .then(() => refreshCallback())
                .catch(
                    requestMessage = "Error"
                )
        }
    }


    return (
        <>

            <form className="border  border-3" onSubmit={(event) => handleSubmit(event)}>
                <h4>Remove tag-value pair</h4>
                <label className="p-2" htmlFor="tag">
                    <div className="label-text ">Tag</div>
                    <input type="text" id="tag" name="tag" placeholder="tag" value={tag}
                           onChange={(e) => handleChangeTag(e)}/>
                </label>

                <label className="p-3" htmlFor="key">
                    <div className="label-text ">Value</div>
                    <input type="text" id="key" name="key" placeholder="key" value={key}
                           onChange={(e) => handleChangeKey(e)}/>
                </label>
                <button className="btn-primary btn">Remove</button>
                {requestMessage && <p className="alert-warning">{requestMessage}</p>}
            </form>
        </>

    )
}

export default RemoveTagForm;