package com.example.dmaker.exception;

import com.example.dmaker.dto.DMakerErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class DMakerExceptionHandler {

    @ExceptionHandler(DMakerException.class)
    public DMakerErrorResponse createError(
            DMakerException e,
            HttpServletRequest request
    ) {
        log.error("errorCode: {}, url: {}, message: {}"
                , e.getDMakerErrorCode()
                , request.getRequestURI()
                , e.getMessage());

        return DMakerErrorResponse.builder()
                .dMakerErrorCode(e.getDMakerErrorCode())
                .message(e.getMessage())
                .build();
    }

}
