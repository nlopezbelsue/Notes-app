const API_URL = "http://localhost:8080/api/notes";

export async function getNotes() {
  const res = await fetch(API_URL);
  return res.json();
}

export async function createNote(note) {
  const res = await fetch(API_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(note),
  });
  return res.json();
}

export async function getNoteById(id) {
  const res = await fetch(`${API_URL}/${id}`);
  return res.json();
}

export async function deleteNote(id) {
  await fetch(`${API_URL}/${id}`, { method: "DELETE" });
}

export async function updateNote(id, note) {
  const res = await fetch(`${API_URL}/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(note),
  });
  return res.json();
}

export async function assignCategories(noteId, categoryIds) {
  const res = await fetch(`${API_URL}/${noteId}/categories`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ categoryIds }),
  });
  return res.json();
}

export async function removeCategoryFromNote(noteId, categoryId) {
  const res = await fetch(
    `${API_URL}/${noteId}/categories/${categoryId}`,
    { method: "DELETE" }
  );

  return res.json();
}

