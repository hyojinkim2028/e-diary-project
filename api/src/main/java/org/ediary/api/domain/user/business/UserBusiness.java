package org.ediary.api.domain.user.business;

import lombok.RequiredArgsConstructor;
import org.ediary.api.common.annotation.Business;
import org.ediary.api.domain.user.controller.model.UserRegisterRequest;
import org.ediary.api.domain.user.controller.model.UserResponse;
import org.ediary.api.domain.user.converter.UserConverter;
import org.ediary.api.domain.user.service.UserService;
import org.ediary.db.user.UserRepository;

@RequiredArgsConstructor
@Business
public class UserBusiness {

    private final UserConverter userConverter;
    private final UserService userService;
    private final UserRepository userRepository;

    // 회원가입
    public UserResponse register(
            UserRegisterRequest request
    ){
        userService.isAvailable(request);

        var entity = userConverter.toEntity(request);
        var newEntity = userService.register(entity);
        var response = userConverter.toResponse(newEntity);

        return response;
    }
}
