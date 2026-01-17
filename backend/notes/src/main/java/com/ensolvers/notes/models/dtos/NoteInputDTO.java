package com.ensolvers.notes.models.dtos;

import lombok.Data;

@Data
public class NoteInputDTO {
    private String title;
    private String content;
    private Boolean archived;
}