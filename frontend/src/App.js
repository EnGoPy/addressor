import './App.css';
import Navbar from "./components/Navbar";
import Header from "./components/Header";
import Footer from "./components/Footer";
import ContentDisplay from "./components/ContentDisplay";

function App() {
    return (
        <div className="container-background h-auto">
            <div className="container-lg p-2">
                <Header/>
                <Navbar/>
                <section className="content p-4">{<ContentDisplay/>}</section>
                <Footer/>
            </div>
        </div>
    );
}

export default App;
