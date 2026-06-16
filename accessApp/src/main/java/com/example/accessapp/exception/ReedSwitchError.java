package com.example.accessapp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReedSwitchError {

    REED_SWITCH_NOT_FOUND("Reed switch does not exist");

    private String message;

}
