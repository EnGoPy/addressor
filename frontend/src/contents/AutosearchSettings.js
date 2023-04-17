import React, {useEffect, useState} from "react";
import Toggler from "../components/Toggler";
import InputNumberProperty from "../components/InputNumberProperty";
import TagApi from "../model/TagApi";
import config from "../config.json";
import {isValidCord, latitudeCordIsValid, longitudeCordIsValid} from "../validation/CoordValidation"
import {coordValidationMessages} from "../validation/ValidationErrorMessages";
import data from "bootstrap/js/src/dom/data";
import AutosearchSettingsModel from "../model/AutosearchSettingsModel";
import {allowedCitiesAreValid} from "../validation/StringValidator";


const AutosearchSettings = () => {

    let [refreshTrigger, setRefreshTrigger] = useState(false);
    let [dataLoaded, setDataLoaded] = useState(false);
    let [settings, setSettings] = useState(new AutosearchSettingsModel(
        false,
        "",
        5,
        5,
        false,
        1.11,
        2.11,
        3.11,
        4.11))

    let [fieldValidation, setFieldValidation] = useState({
        east: null,
        north: null,
        west: null,
        south: null,
        allowedCities: null
    })

    useEffect(() => {
        const controller = new AbortController();
        const signal = controller.signal;
        fetch(config["backend-url"] + config.autoSearchSettings, {signal})
            .then(res => res.json()
            )
            .then(json => {
                    if (!signal.aborted) {
                        setSettings(prev => new AutosearchSettingsModel(
                            json.enableFiltering,
                            json.allowedCities,
                            json.filteringLimit,
                            json.photonApiLimit,
                            json.useBoundary,
                            json.eastBoundary,
                            json.northBoundary,
                            json.westBoundary,
                            json.southBoundary
                        ));
                        setDataLoaded(prev => true);
                    }
                }
            )
            .catch(() => {
                console.log("Unable to fetch autosearch settings from API")
            })
        return () => {
            setDataLoaded(false);
        }
    }, [refreshTrigger]);

    const toggleEnableFiltering = (e, toggledParameter) => {
        const {checked: value} = e.target;
        setSettings({...settings, [toggledParameter]: value});
    }

    const setStringParameter = (e, parameterName) => {
        const {value: inputValue} = e.target;
        console.log("inputValue:" + inputValue);
        setSettings({...settings, [parameterName]: inputValue});
    }

    const setBoundaryCoord = (e, direction) => {
        console.log(e.target.value)
        let coord = e.target.value;

        validateCoordInput(direction, coord)
        switch (direction) {
            case "west":
                setSettings({...settings, ["westBoundary"]: coord});
                break;
            case "north":
                setSettings({...settings, ["northBoundary"]: coord});
                break;
            case "east" :
                setSettings({...settings, ["eastBoundary"]: coord});
                break;
            case "south" :
                setSettings({...settings, ["southBoundary"]: coord});
                break;
        }

    }

    const validateAndSubmit = (event) => {
        event.preventDefault();
        console.log("Form validation start");
        let formValidated = false;
        formValidated = validateCoordInput("east", settings.eastBoundary.valueOf());
        formValidated = formValidated && validateCoordInput("north", settings.northBoundary.valueOf());
        formValidated = formValidated && validateCoordInput("west", settings.westBoundary.valueOf());
        formValidated = formValidated && validateCoordInput("south", settings.southBoundary.valueOf());
        formValidated = formValidated && validateAllowedCities(settings.allowedCities);

        if (!formValidated) {
            console.log("Form is not valid.")
            return;
        }
        console.log("Form is valid. Prepare for sending...")

        let newSettings = new AutosearchSettingsModel(
            settings.enableFiltering,
            settings.allowedCities.trim(","),
            settings.filteringLimit,
            settings.photonApiLimit,
            settings.useBoundary,
            settings.eastBoundary,
            settings.northBoundary,
            settings.westBoundary,
            settings.southBoundary);
        const requestOptions = {
            method: `POST`,
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(newSettings)
        };
        console.log("newSettings :" + newSettings);
        console.log("newSettings stringify :" + JSON.stringify(newSettings));
        fetch(config["backend-url"] + config.autoSearchSettings,
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

    let validateCoordInput = (direction, cord) => {
        let floatCoord = Number.parseFloat(cord);
        console.log("direction " + direction)
        console.log("floatCoord " + floatCoord)
        console.log("coord " + cord)
        // console.log("typeof " + typeof Number(coord) !== "number")
        if (direction === "west" || direction === "east") {
            if (latitudeCordIsValid(cord)) {
                setFieldValidation({...fieldValidation, [direction]: true})
                return true;
            } else {
                setFieldValidation({...fieldValidation, [direction]: false})
                console.log(`Failure for ${direction}`)
                return false
            }
        }
        if (direction === "north" || direction === "south") {
            if (longitudeCordIsValid(cord)) {
                setFieldValidation({...fieldValidation, [direction]: true})
                return true;
            } else {
                setFieldValidation({...fieldValidation, [direction]: false})
                console.log(`Failure for ${direction}`)
                return false;
            }
        }
    }

    const validateAllowedCities = (allowedCities) => {
        let citiesInputIsValid = allowedCitiesAreValid(allowedCities);
        setFieldValidation({...fieldValidation, allowedCities: citiesInputIsValid})
        citiesInputIsValid
            ? console.log("Allowed cities are valid")
            : console.log("Allowed cities are not valid")
        return citiesInputIsValid;
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
                                         toggleLabel="Autosearch Filtering"/>
                                <Toggler callback={(event) => toggleEnableFiltering(event, "useBoundary")}
                                         checked={settings.useBoundary}
                                         toggleLabel="Use coord Boundaries"/>
                            </div>
                            <div className="col">
                                <div className="row">
                                    <InputNumberProperty
                                        callback={(e) => toggleEnableFiltering(e, "photonApiLimit")}
                                        value={settings.photonApiLimit}
                                        id={"useBoundaryToggle"}
                                        text={"Max Photon API response count"}
                                    />
                                </div>
                                <div className="row">
                                    <InputNumberProperty
                                        callback={(e) => toggleEnableFiltering(e, "filteringLimit")}
                                        value={settings.filteringLimit}
                                        id={"flexSwitchCheckChecked"}
                                        text={"Max Adressor API response count"}
                                    />
                                </div>
                                <div className="row">
                                    <InputNumberProperty
                                        callback={(e) => setStringParameter(e, "allowedCities")}
                                        value={settings.allowedCities}
                                        id={"allowedCities"}
                                        text={"Allowed Cities names"}
                                        errorMessage={fieldValidation.allowedCities != null
                                            && !fieldValidation.allowedCities
                                            && coordValidationMessages.ALLOWED_CITIES}
                                    />
                                </div>
                            </div>

                            <fieldset disabled={!settings.useBoundary}>
                                <div className="row">
                                    <InputNumberProperty
                                        callback={(e) => setBoundaryCoord(e, "west")}
                                        value={settings.westBoundary}
                                        id={"flexSwitchCheckChecked"}
                                        text={"West coord"}
                                        errorMessage={fieldValidation.west != null
                                            && !fieldValidation.west
                                            && coordValidationMessages.WEST_BOUNDARY}
                                    />
                                </div>
                                <div className="row">
                                    <InputNumberProperty
                                        callback={(e) => setBoundaryCoord(e, "north")}
                                        value={settings.northBoundary}
                                        id={"flexSwitchCheckChecked"}
                                        text={"North coord"}
                                        errorMessage={fieldValidation.north != null
                                            && !fieldValidation.north
                                            && coordValidationMessages.NORTH_BOUNDARY}
                                    />
                                </div>
                                <div className="row">
                                    <InputNumberProperty
                                        callback={(e) => setBoundaryCoord(e, "east")}
                                        value={settings.eastBoundary}
                                        id={"flexSwitchCheckChecked"}
                                        text={"East coord"}
                                        errorMessage={fieldValidation.east != null
                                            && !fieldValidation.east
                                            && coordValidationMessages.EAST_BOUNDARY}
                                    />
                                </div>
                                <div className="row">
                                    <InputNumberProperty
                                        callback={(e) => setBoundaryCoord(e, "south")}
                                        value={settings.southBoundary}
                                        id={"flexSwitchCheckChecked"}
                                        text={"South coord"}
                                        errorMessage={fieldValidation.south != null
                                            && !fieldValidation.south
                                            && coordValidationMessages.SOUTH_BOUNDARY}
                                    />
                                </div>
                            </fieldset>
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
export default AutosearchSettings;