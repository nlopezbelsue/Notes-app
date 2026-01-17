package com.ensolvers.notes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ensolvers.notes.models.entities.Note;

public interface INoteRepository extends JpaRepository<Note, Long> {
}
