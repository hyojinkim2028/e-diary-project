package org.ediary.api.domain.user.converter;

import lombok.RequiredArgsConstructor;
import org.ediary.api.common.annotation.Converter;
import org.ediary.api.domain.user.controller.model.UserRegisterRequest;
import org.ediary.api.domain.user.controller.model.UserResponse;
import org.ediary.db.user.UserEntity;

@RequiredArgsConstructor
@Converter
public class UserConverter {

    public UserEntity toEntity(
            UserRegisterRequest request
    ){
        return UserEntity.builder()
                .nickname(request.getNickname())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

    }

    public UserResponse toResponse(
            UserEntity userEntity
    ) {
        return UserResponse.builder()
                .nickname(userEntity.getNickname())
                .email(userEntity.getEmail())
                .registeredAt(userEntity.getRegisteredAt())
                .lastLoginAt(userEntity.getLastLoginAt())
                .build();
    }
}
