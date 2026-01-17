import { useEffect, useState } from "react";
import { getNotes, deleteNote, updateNote } from "../api/notesApi";
import { getCategories } from "../api/categoriesApi";
import NoteCard from "../components/NoteCard";

function MyNotesPage() {
  const [notes, setNotes] = useState([]);
  const [categories, setCategories] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState(null);

  const loadNotes = async () => {
    const data = await getNotes();
    setNotes(data.filter(n => !n.archived));
  };

  const loadCategories = async () => {
    const data = await getCategories();
    setCategories(data);
  };

  useEffect(() => {
    loadNotes();
    loadCategories();
  }, []);

  const handleDelete = async id => {
    await deleteNote(id);
    loadNotes();
  };

  const handleArchive = async note => {
    await updateNote(note.id, { ...note, archived: true });
    loadNotes();
  };

  const filteredNotes = selectedCategory
    ? notes.filter(note =>
        note.categories?.some(cat => cat.id === selectedCategory)
      )
    : notes;

  return (
    <>
      <h1 className="page-title">My notes</h1>

      <div className="filter-section">
        <label htmlFor="category-filter">Filter by category:</label>
        <select
          id="category-filter"
          value={selectedCategory || ""}
          onChange={(e) => setSelectedCategory(e.target.value ? parseInt(e.target.value) : null)}
        >
          <option value="">All categories</option>
          {categories.map(cat => (
            <option key={cat.id} value={cat.id}>
              {cat.name}
            </option>
          ))}
        </select>
      </div>

      <div className="notes-grid">
        {filteredNotes.map(note => (
          <NoteCard
            key={note.id}
            note={note}
            onDelete={handleDelete}
            onArchive={handleArchive}
            onCategoriesUpdated={loadNotes}
          />
        ))}
      </div>
    </>
  );
}

export default MyNotesPage;
