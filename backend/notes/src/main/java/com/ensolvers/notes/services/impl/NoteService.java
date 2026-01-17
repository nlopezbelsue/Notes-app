package com.ensolvers.notes.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.ensolvers.notes.models.dtos.AddCategoriesToNoteDTO;
import com.ensolvers.notes.models.dtos.CategoryOutputDTO;
import com.ensolvers.notes.models.dtos.NoteInputDTO;
import com.ensolvers.notes.models.dtos.NoteOutputDTO;
import com.ensolvers.notes.models.entities.Category;
import com.ensolvers.notes.models.entities.Note;
import com.ensolvers.notes.repositories.ICategoryRepository;
import com.ensolvers.notes.repositories.INoteRepository;

import jakarta.transaction.Transactional;

@Service
public class NoteService implements com.ensolvers.notes.services.INoteService {

    private final INoteRepository noteRepository;
    private final ICategoryRepository categoryRepository;

    public NoteService(INoteRepository noteRepository, ICategoryRepository categoryRepository) {
        this.noteRepository = noteRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public NoteOutputDTO createNote(NoteInputDTO input) {
        Note note = new Note(input.getTitle(), input.getContent());

        noteRepository.save(note);

        return noteToOutputDTOMapper(note);
    }

    @Override
    public List<NoteOutputDTO> getAllNotes() {
        List<Note> notes = noteRepository.findAll();
        List<NoteOutputDTO> noteOutputDTOs = new ArrayList<>();

        for (Note note : notes) {
            NoteOutputDTO dto = noteToOutputDTOMapper(note);
            noteOutputDTOs.add(dto);
        }

        return noteOutputDTOs;
    }

    @Override
    public NoteOutputDTO getNoteById(Long id) {
        Note note = noteRepository.findById(id).orElseThrow(() -> new RuntimeException("Note not found"));
        return noteToOutputDTOMapper(note);
    }

    @Override
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }

    @Override
    public NoteOutputDTO updateNote(NoteInputDTO noteInputDTO, Long id) {
        Note note = noteRepository.findById(id).orElseThrow(() -> new RuntimeException("Note not found"));
        note.setTitle(noteInputDTO.getTitle());
        note.setContent(noteInputDTO.getContent());
        note.setArchived(noteInputDTO.getArchived() != null ? noteInputDTO.getArchived() : note.getArchived());
        note.setUpdatedAt(LocalDateTime.now());
        noteRepository.save(note);
        return noteToOutputDTOMapper(note);
    }

    @Override
    @Transactional
    public NoteOutputDTO addCategories(Long noteId, AddCategoriesToNoteDTO dto) {

        Note note = noteRepository.findById(noteId)
            .orElseThrow(() -> new RuntimeException("Note not found"));

        List<Long> uniqueCategoryIds = new ArrayList<>(new HashSet<>(dto.getCategoryIds()));
        
        List<Category> requestedCategories = categoryRepository.findAllById(uniqueCategoryIds);

        if (requestedCategories.size() != uniqueCategoryIds.size()) {
            throw new RuntimeException("One or more categories not found");
        }

        note.getCategories().clear();
        note.getCategories().addAll(requestedCategories);

        Note savedNote = noteRepository.save(note);

        return noteToOutputDTOMapper(savedNote);
    }

    @Override
    @Transactional
    public NoteOutputDTO removeCategory(Long noteId, Long categoryId) {
        Note note = noteRepository.findById(noteId)
            .orElseThrow(() -> new RuntimeException("Note not found"));

        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new RuntimeException("Category not found"));

        note.getCategories().removeIf(cat -> cat.getId().equals(category.getId()));

        Note savedNote = noteRepository.save(note);

        return noteToOutputDTOMapper(savedNote);
    }


    private NoteOutputDTO noteToOutputDTOMapper(Note note) {
        NoteOutputDTO dto = new NoteOutputDTO();
        dto.setId(note.getId());
        dto.setTitle(note.getTitle());
        dto.setContent(note.getContent());
        dto.setArchived(note.getArchived());
        dto.setCreatedAt(note.getCreatedAt().toString());
        dto.setUpdatedAt(note.getUpdatedAt() != null ? note.getUpdatedAt().toString() : null);
        dto.setCategories(
            note.getCategories()
                .stream()
                .map(cat -> {
                    CategoryOutputDTO c = new CategoryOutputDTO();
                    c.setId(cat.getId());
                    c.setName(cat.getName());
                    return c;
                })
                .toList()
        );
        return dto;
    }
}