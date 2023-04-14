import React from "react";

const InputNumberProperty = (props) => {
    let {callback, value, text, id, errorMessage} = props;

    let boxSize = (value) => {
        if(typeof value === "string"){
            // console.log("string")
            return value.length
        }else if (typeof value === "number"){
            // console.log("NUMBER")
            return 5;
        }else{
            return 5;
        }
    }

    return(
        <div className="row">
            <div className="col-auto">
                <input type="text" min="1" max="10" id={id} className="form-control h-100"
                       size={boxSize(value)}
                       onChange={callback}
                       defaultValue={value}/>
            </div>
            <div className="col">
                <label className="form-label" htmlFor={id}>{text} </label>

                <span className="text-sm-end" >
                    {errorMessage}
                </span>
            </div>
        </div>
    )


}

export default InputNumberProperty;