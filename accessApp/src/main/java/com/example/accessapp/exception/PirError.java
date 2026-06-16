package com.example.accessapp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PirError {

    PIR_NOT_FOUND("Pir does not exist");

    private String message;
}
