package com.example.corona.error;

import com.example.corona.constant.ErrorCode;
import com.example.corona.dto.APIErrorResponse;
import com.example.corona.exception.GeneralException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestControllerAdvice(annotations = RestController.class)
public class BaseAPIExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> error(GeneralException e, WebRequest request) {
        ErrorCode errorCode = e.getErrorCode();
        HttpStatus status = errorCode.getHttpStatus();

        return super.handleExceptionInternal(
                e
                , APIErrorResponse.of(false, errorCode.getCode(), errorCode.getMessage(e))
                , HttpHeaders.EMPTY
                , status
                , request
        );
//        return ResponseEntity
//                .status(status)
//                .body(APIErrorResponse.of(
//                        false, errorCode, errorCode.getMessage()
//                ));
    }

    @ExceptionHandler
    public ResponseEntity<Object> exception(Exception e, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorCode errorCode = ErrorCode.INTERNAL_ERROR;

//        return ResponseEntity
//                .status(status)
//                .body(APIErrorResponse.of(
//                        false, errorCode, errorCode.getMessage()
//                ));
        return super.handleExceptionInternal(
                e
                , APIErrorResponse.of(false, errorCode.getCode(), errorCode.getMessage(e))
                , HttpHeaders.EMPTY
                , status
                , request
        );
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorCode errorCode = status.is4xxClientError() ?
                ErrorCode.SPRING_BAD_REQUEST :
                ErrorCode.SPRING_INTERNAL_ERROR;
        return super.handleExceptionInternal(ex
                , APIErrorResponse.of(false, errorCode.getCode(), errorCode.getMessage())
                , headers
                , status
                , request);
    }
}
