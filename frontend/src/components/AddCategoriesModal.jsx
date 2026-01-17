import { useEffect, useState } from "react";
import { getCategories } from "../api/categoriesApi";
import { removeCategoryFromNote, assignCategories } from "../api/notesApi";
import "./AddCategoriesModal.css";

function AddCategoriesModal({ note, isOpen, onClose, onCategoriesAdded }) {
  const [categories, setCategories] = useState([]);
  const [selectedCategories, setSelectedCategories] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    if (isOpen) {
      loadCategories();
      setSelectedCategories(note.categories?.map(c => c.id) || []);
    }
  }, [isOpen, note]);

  const loadCategories = async () => {
    try {
      const data = await getCategories();
      setCategories(data);
    } catch (error) {
      console.error("Error loading categories:", error);
    }
  };

  const handleCategoryChange = (categoryId) => {
    setSelectedCategories(prev => {
      if (prev.includes(categoryId)) {
        return prev.filter(id => id !== categoryId);
      } else {
        return [...prev, categoryId];
      }
    });
  };

  const handleSubmit = async () => {
    setLoading(true);
    try {
      const currentCategoryIds = note.categories?.map(c => c.id) || [];
      const uniqueSelectedIds = [...new Set(selectedCategories)];
      
      for (const categoryId of currentCategoryIds) {
        if (!uniqueSelectedIds.includes(categoryId)) {
          await removeCategoryFromNote(note.id, categoryId);
        }
      }
      
      for (const categoryId of uniqueSelectedIds) {
        await removeCategoryFromNote(note.id, categoryId);
      }
      
      await assignCategories(note.id, uniqueSelectedIds);
      onCategoriesAdded();
      onClose();
    } catch (error) {
      console.error("Error managing categories:", error);
    } finally {
      setLoading(false);
    }
  };

  if (!isOpen) return null;

  return (
    <div className="modal-overlay" onClick={onClose}>
      <div className="modal-content" onClick={e => e.stopPropagation()}>
        <div className="modal-header">
          <h2>Manage Categories for "{note.title}"</h2>
          <button className="modal-close" onClick={onClose}>✕</button>
        </div>

        <div className="modal-body">
          {categories.length === 0 ? (
            <p className="no-categories">No categories available. Create some first</p>
          ) : (
            <div className="categories-checklist">
              {categories.map(category => (
                <label key={category.id} className="category-checkbox">
                  <input
                    type="checkbox"
                    checked={selectedCategories.includes(category.id)}
                    onChange={() => handleCategoryChange(category.id)}
                  />
                  <span>{category.name}</span>
                </label>
              ))}
            </div>
          )}
        </div>

        <div className="modal-footer">
          <button className="btn-cancel" onClick={onClose}>Cancel</button>
          <button className="btn-submit" onClick={handleSubmit} disabled={loading}>
            {loading ? "Saving..." : "Save Changes"}
          </button>
        </div>
      </div>
    </div>
  );
}

export default AddCategoriesModal;
