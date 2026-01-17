package com.ensolvers.notes.models.dtos;

import java.util.List;

import lombok.Data;

@Data
public class NoteOutputDTO {
    private Long id;
    private String title;
    private String content;
    private Boolean archived;
    private String createdAt;
    private String updatedAt;
    private List<CategoryOutputDTO> categories;
}
