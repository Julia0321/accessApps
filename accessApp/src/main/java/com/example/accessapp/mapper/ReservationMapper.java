package com.example.accessapp.mapper;

import com.example.accessapp.dto.ReservationDto;
import com.example.accessapp.model.Reservation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ReservationMapper {

    private final OfficeMapper officeMapper;

    public List<ReservationDto> mapToDto(List<Reservation> reservations) {
        return reservations.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public ReservationDto mapToDto(Reservation theReservation) {
        return ReservationDto.builder()
                .startReservation(theReservation.getStartReservation())
                .endReservation(theReservation.getEndReservation())
                .status(theReservation.getStatus().getValue())
                .accessData(theReservation.getAccessData())
                .offices(theReservation.getOffices().stream().map(officeMapper::mapToDto).collect(Collectors.toList()))
                .user(theReservation.getUser())
                .uuid(theReservation.getUuid())
                .build();
    }
}
