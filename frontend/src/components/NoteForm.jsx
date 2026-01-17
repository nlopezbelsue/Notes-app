
import { useState } from "react";

function NoteForm({
  onSubmit,
  initialValues = { title: "", content: "" },
  submitText = "Create"
}) {
  const [title, setTitle] = useState(initialValues.title);
  const [content, setContent] = useState(initialValues.content);

  const handleSubmit = e => {
    e.preventDefault();
    onSubmit({ title, content });
  };

  return (
    <form className="note-editor" onSubmit={handleSubmit}>
      <input
        className="note-title-input"
        placeholder="Title..."
        value={title}
        onChange={e => setTitle(e.target.value)}
        required
      />

      <textarea
        className="note-content-input"
        placeholder="Write your note here..."
        value={content}
        onChange={e => setContent(e.target.value)}
        rows={10}
        required
      />

      <button className="primary-button" type="submit">
        {submitText}
      </button>
    </form>
  );
}

export default NoteForm;