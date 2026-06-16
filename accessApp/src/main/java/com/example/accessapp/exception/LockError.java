package com.example.accessapp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LockError {

    LOCK_NOT_FOUND("Lock does not exist");

    private String message;
}
