import { useNavigate } from "react-router-dom";
import { createNote } from "../api/notesApi";
import NoteForm from "../components/NoteForm";

function CreateNotePage() {
  const navigate = useNavigate();

  const handleCreate = async note => {
    await createNote(note);
    navigate("/");
  };

  return (
    <>
      <h1 className="page-title">New note</h1>
      <NoteForm onSubmit={handleCreate} />
    </>
  );
}

export default CreateNotePage;
