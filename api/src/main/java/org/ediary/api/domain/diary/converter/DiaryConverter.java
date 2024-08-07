package org.ediary.api.domain.diary.converter;

import org.ediary.api.common.annotation.Converter;
import org.ediary.api.common.error.ErrorCode;
import org.ediary.api.domain.diary.controller.model.DiaryCreateRequest;
import org.ediary.api.domain.diary.controller.model.DiaryResponse;
import org.ediary.api.exception.ApiException;
import org.ediary.db.diary.DiaryEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Converter
public class DiaryConverter {

    public DiaryEntity toEntity(DiaryCreateRequest request, Long memberId) {
        return Optional.ofNullable(request)
                .map(it -> {
                    return DiaryEntity.builder()
                            .memberId(memberId)
                            .emotionId(request.getEmotionId())
                            .content(request.getContent())
                            .createdAt(request.getCreatedAt())
                            .build();
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    // 오버로딩
    public DiaryEntity toEntity(Long id, DiaryCreateRequest request, Long memberId) {
        return Optional.ofNullable(request)
                .map(it -> {
                    return DiaryEntity.builder()
                            .id(id)
                            .memberId(memberId)
                            .emotionId(request.getEmotionId())
                            .content(request.getContent())
                            .createdAt(request.getCreatedAt())
                            .build();
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public DiaryResponse toResponse(DiaryEntity diaryEntity) {
        return Optional.ofNullable(diaryEntity)
                .map(it -> {
                    return DiaryResponse.builder()
                        .id(diaryEntity.getId())
                        .emotionId(diaryEntity.getEmotionId())
                        .content(diaryEntity.getContent())
                        .createdAt(diaryEntity.getCreatedAt())
                        .build();
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    // 오버로딩
    public List<DiaryResponse> toResponse(
            List<DiaryEntity> list
    ) {
        return list.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}

