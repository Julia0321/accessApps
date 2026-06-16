package com.example.accessapp.dto;

import com.example.accessapp.model.AccessData;
import com.example.accessapp.model.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode()
public class ReservationDto {

    @EqualsAndHashCode.Include
    public String uuid;

    public LocalDateTime startReservation;

    public LocalDateTime endReservation;

    public String status;

    public AccessData accessData;

    public List<OfficeDto> offices;

    public User user;


}
