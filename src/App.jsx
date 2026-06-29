import {
    BrowserRouter,
    Routes,
    Route
} from "react-router-dom";

import NavbarComponent from "./components/NavbarComponent";

import Home from "./pages/Home";
import AdminDashboard from "./pages/AdminDashboard";
import CandidatePortal from "./pages/CandidatePortal";

function App() {

    return (

        <BrowserRouter>

            <NavbarComponent />

            <Routes>

                <Route
                    path="/"
                    element={<Home />}
                />

                <Route
                    path="/admin"
                    element={<AdminDashboard />}
                />

                <Route
                    path="/candidate"
                    element={<CandidatePortal />}
                />

            </Routes>

        </BrowserRouter>
    );
}

export default App;