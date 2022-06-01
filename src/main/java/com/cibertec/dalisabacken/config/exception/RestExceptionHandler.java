package com.cibertec.dalisabacken.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    protected ResponseEntity<CErrorResponse> runtimeExceptionHandler(RuntimeException exc) {
        var error = CErrorResponse.builder().code(500).message(exc.getMessage()).build();
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = CRequestException.class)
    protected ResponseEntity<CErrorResponse> runtimeHandlerException(CRequestException exc) {
        var error = CErrorResponse.builder().code(exc.getCode()).message(exc.getMessage()).build();
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = CBusinessException.class)
    protected ResponseEntity<CErrorResponse> BusinessHandlerException(CBusinessException exc) {
        var error = CErrorResponse.builder().code(exc.getStatus().value()).status(exc.getStatus().name()).message(exc.getMessage()).build();
        return new ResponseEntity<>(error,exc.getStatus());
    }
}
