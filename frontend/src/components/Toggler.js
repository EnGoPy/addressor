import React from "react";

const Toggler = (props) => {

    const {callback, checked, toggleLabel} = props;

    return(
        <div className="form-check form-switch">
            <input className="form-check-input" type="checkbox" role="switch" id={toggleLabel}
                   onChange={callback}
                   defaultChecked={checked}/>

            <label className="form-check-label" htmlFor={toggleLabel}>{toggleLabel}</label>
        </div>
    )
}

export default Toggler;