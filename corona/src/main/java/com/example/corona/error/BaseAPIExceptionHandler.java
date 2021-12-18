package com.example.corona.error;

import com.example.corona.constant.ErrorCode;
import com.example.corona.dto.APIErrorResponse;
import com.example.corona.exception.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestControllerAdvice(annotations = RestController.class)
public class BaseAPIExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<APIErrorResponse> error(GeneralException e) {
        ErrorCode errorCode = e.getErrorCode();
        HttpStatus status = errorCode.getHttpStatus();

        return ResponseEntity
                .status(status)
                .body(APIErrorResponse.of(
                        false, errorCode, errorCode.getMessage()
                ));
    }

    @ExceptionHandler
    public ResponseEntity<APIErrorResponse> exception(Exception e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorCode errorCode = ErrorCode.INTERNAL_ERROR;

        return ResponseEntity
                .status(status)
                .body(APIErrorResponse.of(
                        false, errorCode, errorCode.getMessage()
                ));
    }



}
