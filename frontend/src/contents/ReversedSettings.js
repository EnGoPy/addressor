import React, {useEffect, useState} from "react";
import Toggler from "../components/Toggler";
import InputNumberProperty from "../components/InputNumberProperty";
import {coordValidationMessages} from "../validation/ValidationErrorMessages";
import ReversedSettingsModel from "../model/ReversedSettingsModel";
import config from "../config.json";
const ReversedSettings = () => {

    let [refreshTrigger, setRefreshTrigger] = useState(false);
    let [dataLoaded, setDataLoaded] = useState(false);
    let [settings, setSettings] = useState(new ReversedSettingsModel(
        false
    ))

    useEffect(() => {
        const controller = new AbortController();
        const signal = controller.signal;
        fetch(config["backend-url"] + config.reversedSettings, {signal})
            .then(res => res.json())
            .then(json => {
                if(!signal.aborted){
                    setSettings(prevState => new ReversedSettingsModel(
                        json.enableFiltering
                    ));
                    setDataLoaded(prev => true);
                }
            })
            .catch(() => {
                console.log("Unable to fetch reversed settings from API")
            })
        return () => {
            setDataLoaded(false);
        }
    }, [refreshTrigger])

    const toggleEnableFiltering = (e, toggledParameter) => {
        const {checked: value} = e.target;
        setSettings({...settings, [toggledParameter]: value});
    }

    const validateAndSubmit = () => {
        let newSettings = new ReversedSettingsModel(settings.enableFiltering);
        const requestOptions = {
            method: `POST`,
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(newSettings)
        };
        fetch(config["backend-url"] + config.reversedSettings,
            requestOptions)
            .then((res) => {
                console.log("Sending to API!!")
                console.log("res" + res.json())
            })
            .then(() => setRefreshTrigger(prev => !prev))
            .catch(() => {
                    console.log("Error during sending object")
                    setRefreshTrigger(prev => !prev)
                }
            )
    }

    return (
        <>
            <p>
                <button className="btn btn-primary" type="button" data-bs-toggle="collapse"
                        data-bs-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                    Settings
                </button>
            </p>
            <div className="row collapse" id="collapseExample">
                {dataLoaded ?
                    <form onSubmit={(event) => validateAndSubmit(event)}>
                        <div className="row border border-primary border-1 mx-3 py-1">
                            <div className="col">
                                <Toggler callback={(event) => toggleEnableFiltering(event, "enableFiltering")}
                                         checked={settings.enableFiltering}
                                         toggleLabel="Reversed Filtering"/>
                            </div>
                            <button className="btn btn-primary">
                                Validate & Save
                            </button>
                        </div>
                    </form>
                    : "Didn't fetch data yet..."}
            </div>
        </>
    )
}

export default ReversedSettings;