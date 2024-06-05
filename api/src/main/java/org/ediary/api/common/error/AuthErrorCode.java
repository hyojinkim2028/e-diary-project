package org.ediary.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthErrorCode implements ErrorCodeIfs{

    PASSWORD_MISMATCH(400,1501,"비밀번호 불일치"),
    EXIST_USER(400,1502,"이미 존재하는 유저"),

    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;

}
