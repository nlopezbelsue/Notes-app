const API_URL = "http://localhost:8080/api/categories";

export async function getCategories() {
  const res = await fetch(API_URL);
  return res.json();
}

export async function createCategory(category) {
  const res = await fetch(API_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(category),
  });
  return res.json();
}

export async function deleteCategory(id) {
  await fetch(`${API_URL}/${id}`, { method: "DELETE" });
}
