package org.ediary.api.domain.diary.business;

import lombok.RequiredArgsConstructor;
import org.ediary.api.common.annotation.Business;
import org.ediary.api.domain.diary.controller.model.DiaryCreateRequest;
import org.ediary.api.domain.diary.controller.model.DiaryResponse;
import org.ediary.api.domain.diary.converter.DiaryConverter;
import org.ediary.api.domain.diary.service.DiaryService;
import org.ediary.db.member.model.Member;

@Business
@RequiredArgsConstructor
public class DiaryBusiness {

    private final DiaryService diaryService;
    private final DiaryConverter diaryConverter;

    // 다이어리 리퀘스트를 엔티티로
    // 엔티티 저장
    // 엔티티를 레스폰스로 리턴

    public DiaryResponse create(
            DiaryCreateRequest diaryCreateRequest,
            Long userId
    ) {
        // req -> entity -> res
        var entity = diaryConverter.toEntity(diaryCreateRequest, userId);
        var newEntity = diaryService.create(entity);
        var response = diaryConverter.toResponse(newEntity);

        return response;
    }

    public DiaryResponse update(
            Long id,
            DiaryCreateRequest diaryCreateRequest,
            Long memberId
    ) {
        // req -> entity -> res
        var entity = diaryConverter.toEntity(id, diaryCreateRequest, memberId);
        var newEntity = diaryService.update(id, entity);
        var response = diaryConverter.toResponse(newEntity);

        return response;
    }

}
