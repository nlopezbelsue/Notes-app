import { useEffect, useState } from "react";
import {
  getCategories,
  createCategory,
  deleteCategory
} from "../api/categoriesApi";

function CategoriesPage() {
  const [categories, setCategories] = useState([]);
  const [name, setName] = useState("");

  const loadCategories = async () => {
    const data = await getCategories();
    setCategories(data);
  };

  useEffect(() => {
    loadCategories();
  }, []);

  const handleCreate = async e => {
    e.preventDefault();
    if (!name.trim()) return;

    await createCategory({ name });
    setName("");
    loadCategories();
  };

  const handleDelete = async id => {
    await deleteCategory(id);
    loadCategories();
  };

  return (
    <div className="content">
      <h1 className="page-title">Categories</h1>

      <form onSubmit={handleCreate} className="category-form">
        <input
          value={name}
          onChange={e => setName(e.target.value)}
          placeholder="Category name"
        />
        <button type="submit">Create</button>
      </form>

      <ul className="categories-list">
        {categories.map(cat => (
          <li key={cat.id}>
            {cat.name}
            <button onClick={() => handleDelete(cat.id)}>✕</button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default CategoriesPage;
