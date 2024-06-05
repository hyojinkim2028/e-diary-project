package org.ediary.api.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.ediary.api.common.api.Api;
import org.ediary.api.common.error.ErrorCode;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice // 예외를 이곳으로 다 끌어모음
@Order(value = Integer.MAX_VALUE) // 가장 후순위로 실행
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Api<Object>> exception(
            Exception exception
    ) {
        log.error("", exception);

        return ResponseEntity
                .status(500)
                .body(
                        Api.ERROR(ErrorCode.SERVER_ERROR)
                );
    }
}
