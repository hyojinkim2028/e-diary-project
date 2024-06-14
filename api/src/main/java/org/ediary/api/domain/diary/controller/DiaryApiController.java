package org.ediary.api.domain.diary.controller;


import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ediary.api.common.api.Api;
import org.ediary.api.domain.diary.business.DiaryBusiness;
import org.ediary.api.domain.diary.controller.model.DiaryCreateRequest;
import org.ediary.api.domain.diary.controller.model.DiaryResponse;
import org.ediary.api.domain.diary.service.DiaryService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/diary")
public class DiaryApiController {

    private final DiaryBusiness diaryBusiness;
    private final DiaryService diaryService;

    @PostMapping("")
    public Api<DiaryResponse> create(
            @Parameter(hidden = true)
            @AuthenticationPrincipal UserDetails userDetails,

            @Valid @RequestBody Api<DiaryCreateRequest> request
    ){
        var memberId = Long.parseLong(userDetails.getUsername());
        var response = diaryBusiness.create(request.getBody(), memberId);

        return Api.OK(response);
    }

    // 특정 diary 조회
    @GetMapping("/{id}")
    public Api<DiaryResponse> read(
            @Parameter(hidden = true)
            @AuthenticationPrincipal UserDetails userDetails,

            @PathVariable Long id
    ){
        var response = diaryService.read(id);
        return Api.OK(response);
    }

    // 해당하는 멤버의 diary 전체조회
    @GetMapping("")
    public Api<List<DiaryResponse>> readAll(
            @Parameter(hidden = true)
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        var memberId = Long.parseLong(userDetails.getUsername());
        var response = diaryService.readAll(memberId);
        return Api.OK(response);
    }

    @PutMapping("/{id}")
    public Api<DiaryResponse> update(
            @Parameter(hidden = true)
            @AuthenticationPrincipal UserDetails userDetails,

            @PathVariable Long id,
            @Valid @RequestBody Api<DiaryCreateRequest> request
    ){
        var memberId = Long.parseLong(userDetails.getUsername());
        var response = diaryBusiness.update(id, request.getBody(), memberId);

        return Api.OK(response);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @Parameter(hidden = true)
            @AuthenticationPrincipal UserDetails userDetails,

            @PathVariable Long id
    ){
        diaryService.delete(id);
    }

}
