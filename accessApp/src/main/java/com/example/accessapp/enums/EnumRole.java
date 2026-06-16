package com.example.accessapp.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumRole {

    ADMIN("ADMIN"),
    MANAGEMENT_USER("MANAGEMENT_USER"),
    USER("USER");

    private final String value;


}
