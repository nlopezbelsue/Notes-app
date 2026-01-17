import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { getNoteById, updateNote } from "../api/notesApi";
import NoteForm from "../components/NoteForm";

function EditNotePage() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [note, setNote] = useState(null);

  useEffect(() => {
    const loadNote = async () => {
      const data = await getNoteById(id);
      setNote(data);
    };
    loadNote();
  }, [id]);

  const handleUpdate = async updatedNote => {
    await updateNote(id, { ...note, ...updatedNote });
    navigate("/");
  };

  if (!note) return <p>Loading...</p>;

  return (
    <>
      <h1 className="page-title">Edit note</h1>
      <NoteForm
        initialValues={note}
        onSubmit={handleUpdate}
        submitText="Save"
      />
    </>
  );
}

export default EditNotePage;
