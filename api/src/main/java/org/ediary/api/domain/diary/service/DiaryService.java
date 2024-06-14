package org.ediary.api.domain.diary.service;

import lombok.RequiredArgsConstructor;
import org.ediary.api.common.error.ErrorCode;
import org.ediary.api.domain.diary.controller.model.DiaryResponse;
import org.ediary.api.domain.diary.converter.DiaryConverter;
import org.ediary.api.exception.ApiException;
import org.ediary.db.diary.DiaryEntity;
import org.ediary.db.diary.DiaryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final DiaryConverter diaryConverter;

    public DiaryEntity create(
            DiaryEntity diaryEntity
    ) {
        return Optional.ofNullable(diaryEntity)
                .map( diary -> {
                    diary.setCreatedAt(LocalDateTime.now());
                    return diaryRepository.save(diary);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));

    }

    public DiaryResponse read(Long id){
        var isExist = diaryRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
        return diaryConverter.toResponse(isExist);
    }

    public List<DiaryResponse> readAll(Long memberId){
        var allDiary = diaryRepository.findAllByMemberId(memberId);
        return diaryConverter.toResponse(allDiary);
    }

    public DiaryEntity update(
            Long id,
            DiaryEntity diaryEntity
    ) {

        var isExist = diaryRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));

        return Optional.ofNullable(diaryEntity)
                .map( diary -> {
                    diary.setCreatedAt(isExist.getCreatedAt());
                    diary.setUpdatedAt(LocalDateTime.now());
                    return diaryRepository.save(diary);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));

    }

    public void delete(Long id) {
        var isExist = diaryRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
        diaryRepository.delete(isExist);
    }
}
