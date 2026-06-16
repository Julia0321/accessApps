package com.example.accessapp.service;

import com.example.accessapp.dto.CreateReservationDto;
import com.example.accessapp.dto.ReservationDto;

import java.util.List;

public interface ReservationService {

    ReservationDto saveReservation(CreateReservationDto theReservationDto, String principalName);

    List<ReservationDto> findAllByStatusPending();

    ReservationDto updateStatus(String reservationUuid);

    ReservationDto updateRejectedStatus(String reservationUuid);

    ReservationDto updateCanceledStatus(String reservationUuid);

    List<ReservationDto> findAllByStatusAccepted();

    List<ReservationDto> findAllByStatusRejected();

    List<ReservationDto> findAllByStatusCanceled();

    List<ReservationDto> findAllAcceptedReservationsByUser(String principalName);
}
