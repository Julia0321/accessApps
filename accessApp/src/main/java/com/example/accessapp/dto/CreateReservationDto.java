package com.example.accessapp.dto;

import com.example.accessapp.validation.ReservationDates;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ReservationDates
public class CreateReservationDto {

    @NotNull(message = "Office is required")
    public String officeUuid;

    @Future(message = "Choose a future date for start a reservation")
    @NotNull(message = "Start date is required")
    public LocalDateTime startDate;

    @Future(message = "Choose a future date for end a reservation")
    @NotNull(message = "End date is required")
    public LocalDateTime endDate;

}
