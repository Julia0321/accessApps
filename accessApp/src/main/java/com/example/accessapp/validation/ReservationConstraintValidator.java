package com.example.accessapp.validation;


import com.example.accessapp.dto.CreateReservationDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class ReservationConstraintValidator implements ConstraintValidator<ReservationDates, CreateReservationDto> {

    @Override
    public void initialize(ReservationDates constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(CreateReservationDto reservation, ConstraintValidatorContext context) {

        if (reservation == null) return false;

        LocalDateTime start = reservation.getStartDate();
        LocalDateTime end = reservation.getEndDate();

        if (start == null || end == null) return false;

        return start.isBefore(end);
    }
}
