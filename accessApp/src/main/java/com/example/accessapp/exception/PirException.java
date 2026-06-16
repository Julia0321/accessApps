package com.example.accessapp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PirException extends RuntimeException {
    private PirError pirError;
}
