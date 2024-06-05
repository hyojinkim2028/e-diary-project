package org.ediary.api.domain.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ediary.api.domain.user.business.UserBusiness;
import org.ediary.api.domain.user.controller.model.UserRegisterRequest;
import org.ediary.api.domain.user.controller.model.UserResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/open-api/user")
public class UserOpenApiController {

    private final UserBusiness userBusiness;

    @PostMapping("/")
    public UserResponse register(
            @Valid
            @RequestBody UserRegisterRequest request
    ){
        System.out.println(request);
        var response = userBusiness.register(request);
        return response;
    }


}
