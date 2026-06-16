package com.example.accessapp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LockException extends RuntimeException {
    private LockError lockError;
}
