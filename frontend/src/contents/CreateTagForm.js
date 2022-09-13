import React, {useState} from "react";
import config from "../config.json";
import "./AutosearchForm.js.css"

const CreateTagForm = (props) => {

    const [tag, setTag] = useState();
    const [key, setKey] = useState();
    let postMessage = "";

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
            const requestOptions = {
                method: `POST`,
                headers: {
                    "Content-Type": "application/json"
                },
                body: `{"${tag}":"${key}"}`
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
                    postMessage = "Error"
                )
        }
    }


    return (
        <>

            <form className="border  border-3" onSubmit={(event) => handleSubmit(event)}>
                <h4>Add tag-value pair</h4>
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
                <button className="btn-primary btn">Add tag</button>
                {postMessage && <p className="alert-warning">{postMessage}</p>}
            </form>
        </>

    )
}

export default CreateTagForm;