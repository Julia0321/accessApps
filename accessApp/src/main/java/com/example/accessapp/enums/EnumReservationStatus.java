package com.example.accessapp.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumReservationStatus {

    PENDING("PENDING"),
    ACCEPTED("ACCEPTED"),
    REJECTED("REJECTED"),
    CANCELED("CANCELED");

    private final String value;

    public static EnumReservationStatus fromValue(String value) {
        for (EnumReservationStatus status : EnumReservationStatus.values()) {
            if (status.getValue().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Nieznana wartość statusu: " + value);
    }

}
