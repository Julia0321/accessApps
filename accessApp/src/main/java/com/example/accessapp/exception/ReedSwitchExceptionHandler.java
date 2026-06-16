package com.example.accessapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ReedSwitchExceptionHandler {

    @ExceptionHandler(value = ReedSwitchException.class)
    public ResponseEntity<ErrorInfo> handleException(ReedSwitchException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorInfo(e.getReedSwitchError().getMessage()));
    }
}
