package com.ensolvers.notes.models.dtos;

import lombok.Data;
import java.util.List;

@Data
public class AddCategoriesToNoteDTO {
    private List<Long> categoryIds;
}
