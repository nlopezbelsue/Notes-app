import { useState } from "react";
import { useNavigate } from "react-router-dom";
import AddCategoriesModal from "./AddCategoriesModal";
import "./NoteCard.css";

function NoteCard({ note, onDelete, onArchive, onCategoriesUpdated }) {
  const navigate = useNavigate();
  const [showCategoryModal, setShowCategoryModal] = useState(false);

  const handleCategoriesAdded = () => {
    if (onCategoriesUpdated) {
      onCategoriesUpdated();
    }
  };

  return (
    <>
      <div className="note-card">
        <h3>{note.title}</h3>

        {note.categories?.length > 0 && (
          <div className="note-categories">
            {note.categories.map(cat => (
              <span key={cat.id} className="category-pill">
                {cat.name}
              </span>
            ))}
          </div>
        )}

        <p>{note.content}</p>

          <div className="actions">
            <button className="btn-edit" onClick={() => navigate(`/edit/${note.id}`)}>
              Edit
            </button>

            <button
              className="btn-categories"
              onClick={() => setShowCategoryModal(true)}
            >
              Categories
            </button>

            <button className="btn-delete" onClick={() => onDelete(note.id)}>
              Delete
            </button>

            <button className="btn-archive" onClick={() => onArchive(note)}>
              {note.archived ? "Unarchive" : "Archive"}
            </button>
          </div>


      </div> 

      <AddCategoriesModal
        note={note}
        isOpen={showCategoryModal}
        onClose={() => setShowCategoryModal(false)}
        onCategoriesAdded={handleCategoriesAdded}
      />
    </>
  );
}

export default NoteCard;
