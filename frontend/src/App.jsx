import { BrowserRouter, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import MyNotesPage from "./pages/MyNotesPage";
import ArchivedNotesPage from "./pages/ArchivedNotesPage";
import CreateNotePage from "./pages/CreateNotePage";
import EditNotePage from "./pages/EditNotePage";
import CategoriesPage from "./pages/CategoriesPage";
import "./App.css";

function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <main className="content">
        <Routes>
          <Route path="/" element={<MyNotesPage />} />
          <Route path="/archived" element={<ArchivedNotesPage />} />
          <Route path="/create" element={<CreateNotePage />} />
          <Route path="/edit/:id" element={<EditNotePage />} />
          <Route path="/categories" element={<CategoriesPage />} />
        </Routes>
      </main>
    </BrowserRouter>
  );
}

export default App;
