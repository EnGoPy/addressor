import React from "react";
import {Routes, Route} from "react-router-dom";
import Home from "../contents/Home";
import Reversed from "../contents/Reversed";
import Autosearch from "../contents/Autosearch";
import About from "../contents/About";
import ErrorPage from "../contents/ErrorPage";
import "./ContentDisplay"

const ContentDisplay = () => {
    return (
        <>
            <Routes>
                <Route path="/" exact element={<Home/>} />
                <Route path="/reversed" exact element={<Reversed />} />
                <Route path="/autosearch" exact element={<Autosearch />} />
                <Route path="/about" exact element={<About />} />
                <Route path="*" element={<ErrorPage />} />
            </Routes>
        </>
    )
}

export default ContentDisplay;