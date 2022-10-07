import React from "react";
import "./Header.css";

const Header = () => {
    return (
        <div className="p-4 bg-primary text-white text-lg-start rounded-top">
            <h1>Welcome to addressor</h1>
            <p>Your help to mainain <a href="https://photon.komoot.io">komoot/photon</a> tool!</p>
        </div>
    )
}
export default Header;