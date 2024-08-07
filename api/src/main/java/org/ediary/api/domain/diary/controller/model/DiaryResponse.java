package org.ediary.api.domain.diary.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiaryResponse {

    private Long id;

    private String emotionId;

    private String content;

    private LocalDateTime createdAt;

}
