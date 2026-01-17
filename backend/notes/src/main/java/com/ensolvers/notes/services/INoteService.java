package com.ensolvers.notes.services;

import java.util.List;

import org.aspectj.weaver.ast.Not;

import com.ensolvers.notes.models.dtos.AddCategoriesToNoteDTO;
import com.ensolvers.notes.models.dtos.NoteInputDTO;
import com.ensolvers.notes.models.dtos.NoteOutputDTO;

public interface INoteService {
    public NoteOutputDTO createNote(NoteInputDTO input);
    public List<NoteOutputDTO> getAllNotes();
    public NoteOutputDTO getNoteById(Long id);
    public void deleteNote(Long id);
    public NoteOutputDTO updateNote(NoteInputDTO noteInputDTO, Long id);
    public NoteOutputDTO addCategories(Long noteId, AddCategoriesToNoteDTO dto);
    public NoteOutputDTO removeCategory(Long noteId, Long categoryId);
}
