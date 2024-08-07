package org.ediary.api.domain.diary.controller.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiaryCreateRequest {

    @NotNull
    private LocalDateTime createdAt;

    @NotBlank
    private String emotionId;

    @NotBlank
    private String content;
}
