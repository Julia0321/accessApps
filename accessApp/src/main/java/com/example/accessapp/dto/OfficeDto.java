package com.example.accessapp.dto;

import com.example.accessapp.model.Building;
import com.example.accessapp.model.Reservation;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode()
public class OfficeDto {

    public String numOfOffice;

    public String description;

    public List<Reservation> reservations;

    public Building building;

    @EqualsAndHashCode.Include
    public String uuid;
}
