import logo from './logo.svg';
import './App.css';
import Navbar from "./components/Navbar";
import Header from "./components/Header";
import Footer from "./components/Footer";
import ContentDisplay from "./components/ContentDisplay";
import {BrowserRouter as Router} from "react-router-dom";

function App() {
    return (
        <div className="container-background h-auto">
            <div className="container-lg p-2">
                <Router>
                    <Header/>
                    <Navbar/>
                    <section className="content p-4">{<ContentDisplay/>}</section>
                    <Footer/>
                </Router>
            </div>
        </div>
    );
}

export default App;
