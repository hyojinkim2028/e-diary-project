package org.ediary.api.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.ediary.api.common.error.AuthErrorCode;
import org.ediary.api.domain.user.controller.model.UserRegisterRequest;
import org.ediary.api.exception.ApiException;
import org.ediary.db.user.UserEntity;
import org.ediary.db.user.UserRepository;
import org.ediary.db.user.enums.UserStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    public final PasswordEncoder passwordEncoder;

    public void isAvailable(UserRegisterRequest request){

        // 비번 일치여부
        if(!Objects.equals(request.getPassword(), request.getConfirmPassword())) {
            throw new ApiException(AuthErrorCode.PASSWORD_MISMATCH);
        }

        // 유저 존재 여부
        var isExistUser = userRepository.findFirstByEmailAndStatusOrderByUserIdDesc(request.getEmail(), UserStatus.REGISTERED);
        if(isExistUser.isPresent()) {
            throw new ApiException(AuthErrorCode.EXIST_USER);
        }

    }

    public UserEntity register(
            UserEntity userEntity
    ){

        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setStatus(UserStatus.REGISTERED);
        userEntity.setRegisteredAt(LocalDateTime.now());

        return userRepository.save(userEntity);
    }
}
