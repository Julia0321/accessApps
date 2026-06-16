package com.example.accessapp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ReedSwitchException extends RuntimeException {
    private ReedSwitchError reedSwitchError;
}
