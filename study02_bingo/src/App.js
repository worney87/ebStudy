import "./App.css";
import 'bootstrap/dist/css/bootstrap.min.css';
import MainSection from "./components/MainSection";


function App() {
    return (
        <div className="App">

            <header className="HeaderStyle">
                <h1>Bingo Game</h1>
            </header>

            <main className="MainStyle">
                <MainSection/>
            </main>

        </div>
    );
}

export default App;
