package com.example.corona.error;

import com.example.corona.constant.ErrorCode;
import com.example.corona.dto.APIErrorResponse;
import com.example.corona.exception.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler
    public ModelAndView general(GeneralException e, HttpServletResponse response) {
        HttpStatus status = HttpStatus.valueOf(response.getStatus());
        ErrorCode errorCode = ErrorCode.valueOf(status);

        return new ModelAndView("error"
                , Map.of(
                "statusCode", status.value(),
                "errorCode", errorCode,
                "message", errorCode.getMessage(e)
        )
                , status
        );
    }

    @ExceptionHandler
    public ModelAndView general(Exception e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorCode errorCode = ErrorCode.INTERNAL_ERROR;

        return new ModelAndView("error"
                , Map.of(
                "statusCode", status.value(),
                "errorCode", errorCode,
                "message", errorCode.getMessage(e)
        )
                , status
        );
    }



}
