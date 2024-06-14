package org.ediary.api.domain.diary.controller.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiaryCreateRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
