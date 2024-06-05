package org.ediary.api.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.ediary.api.common.api.Api;
import org.ediary.api.exception.ApiException;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice // 에러 수집
@Order(value = Integer.MIN_VALUE) // 최우선 처리
public class ApiExceptionHandler {

    @ExceptionHandler(value = ApiException.class) // 해당 ApiException.class 에서 발생하는 예외 모두 캐치
    public ResponseEntity<Api<Object>> apiException(
            ApiException apiException
    ) {
        log.error("", apiException);

        var errorCode = apiException.getErrorCodeIfs();

        return ResponseEntity
                .status(errorCode.getHttpStatusCode())
                .body(
                        Api.ERROR(errorCode, apiException.getErrorDescription())
                );
    }
}
