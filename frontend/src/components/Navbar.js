import React from "react";
import './Navbar.css';
import {NavLink} from "react-router-dom";

const Navbar = () => {
    return (
        <React.Fragment>
            <nav className="navbar nav-custom">
                <span className="navbar-collapse">
                    <div className="nav">
                        <NavLink className="nav-item nav-link text-white" to={"/reversed"}>Reversed</NavLink>
                        <NavLink className="nav-item nav-link text-white" to={"/autosearch"}>AutoSearch</NavLink>
                        <NavLink className="nav-item nav-link text-white" to={"/about"}>About</NavLink>
                    </div>
                </span>
            </nav>
        </React.Fragment>
    );
}
export default Navbar;