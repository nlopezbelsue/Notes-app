package com.ensolvers.notes.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ensolvers.notes.models.dtos.AddCategoriesToNoteDTO;
import com.ensolvers.notes.models.dtos.NoteInputDTO;
import com.ensolvers.notes.models.dtos.NoteOutputDTO;
import com.ensolvers.notes.services.INoteService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final INoteService noteService;

    public NoteController(INoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/health-check")
    public String testConnection() {
        return "API is alive";
    }

    @PostMapping
    public NoteOutputDTO createNote(@RequestBody NoteInputDTO noteInputDTO) {
        return noteService.createNote(noteInputDTO);
    }

    @GetMapping
    public List<NoteOutputDTO> getAllNotes() {
        return noteService.getAllNotes();
    }

    @GetMapping("/{id}")
    public NoteOutputDTO getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return "Note with id: " + id + " deleted";
    }

    @PutMapping("/{id}")
    public NoteOutputDTO updateNote(@RequestBody NoteInputDTO noteInputDTO, @PathVariable Long id) {
        return noteService.updateNote(noteInputDTO, id);
    }

    @PutMapping("/{noteId}/categories")
    public NoteOutputDTO addCategoriesToNote(@PathVariable Long noteId, @RequestBody AddCategoriesToNoteDTO dto) {
        return noteService.addCategories(noteId, dto);
    }

    @DeleteMapping("/{noteId}/categories/{categoryId}")
    public NoteOutputDTO removeCategory(@PathVariable Long noteId, @PathVariable Long categoryId) {
        return noteService.removeCategory(noteId, categoryId);
    }


}