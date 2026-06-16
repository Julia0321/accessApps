package com.example.accessapp.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ReservationConstraintValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ReservationDates {

    public String message() default "Start time must be earlier than end time";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
