package com.example.accessapp.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode()
public class AccessDataDto {

    public ReservationDto reservation;

    public String uuid;

}
