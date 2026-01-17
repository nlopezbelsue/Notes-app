package com.ensolvers.notes.models.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.ensolvers.notes.models.entities.Category;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "notes")
@Getter
@Setter
@NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private Boolean archived;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @ManyToMany
    @JoinTable(
        name = "note_categories",
        joinColumns = @JoinColumn(name = "note_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories = new ArrayList<>();

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
        this.archived = false;
        this.createdAt = LocalDateTime.now();
    }

}