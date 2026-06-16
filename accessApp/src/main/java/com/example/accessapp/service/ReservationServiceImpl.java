package com.example.accessapp.service;

import com.example.accessapp.dto.CreateReservationDto;
import com.example.accessapp.dto.ReservationDto;
import com.example.accessapp.enums.EnumReservationStatus;
import com.example.accessapp.mapper.ReservationMapper;
import com.example.accessapp.model.AccessData;
import com.example.accessapp.model.Office;
import com.example.accessapp.model.Reservation;
import com.example.accessapp.repository.OfficeRepository;
import com.example.accessapp.repository.ReservationRepository;
import com.example.accessapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final OfficeRepository officeRepository;
    private final UserRepository userRepository;

    @Override
    public ReservationDto saveReservation(CreateReservationDto theReservationDto, String principalName) {

        Reservation reservation = Reservation.builder()
                .startReservation(theReservationDto.getStartDate())
                .endReservation(theReservationDto.getEndDate().plusMinutes(2))
                .user(userRepository.findUserByEmail(principalName).orElseThrow(() -> new RuntimeException("User not found")))
                .build();

        Office office = officeRepository.findByUuid(theReservationDto.officeUuid).orElseThrow(() -> new RuntimeException("Office not found"));
        reservation.addOffice(office);

        return reservationMapper.mapToDto(reservationRepository.save(reservation));

    }

    @Override
    public List<ReservationDto> findAllByStatusPending() {

        List<ReservationDto> reservations = reservationMapper.mapToDto(reservationRepository.findAllByStatus(EnumReservationStatus.PENDING));

        for(ReservationDto reservation : reservations){
            reservation.setEndReservation(reservation.getEndReservation().minusMinutes(2));
        }

        return reservations;
    }

    @Override
    public ReservationDto updateStatus(String reservationUuid) {
        Reservation reservation = reservationRepository.findByUuid(reservationUuid);

        return reservationMapper.mapToDto(reservationRepository.save(
                reservation.toBuilder()
                        .status(EnumReservationStatus.ACCEPTED)
                        .accessData(new AccessData())
                        .build()));
    }

    @Override
    public ReservationDto updateRejectedStatus(String reservationUuid) {
        Reservation reservation = reservationRepository.findByUuid(reservationUuid);

        return reservationMapper.mapToDto(reservationRepository.save(
                reservation.toBuilder()
                        .status(EnumReservationStatus.REJECTED)
                        .accessData(null)
                        .build()));
    }

    @Override
    public ReservationDto updateCanceledStatus(String reservationUuid) {
        Reservation reservation = reservationRepository.findByUuid(reservationUuid);

        return reservationMapper.mapToDto(reservationRepository.save(
                reservation.toBuilder()
                        .status(EnumReservationStatus.CANCELED)
                        .accessData(null)
                        .build()));
    }

    @Override
    public List<ReservationDto> findAllByStatusAccepted() {

        List<ReservationDto> reservations = reservationMapper.mapToDto(reservationRepository.findAllByStatus(EnumReservationStatus.ACCEPTED));

        for(ReservationDto reservation : reservations){
            reservation.setEndReservation(reservation.getEndReservation().minusMinutes(2));
        }

        return reservations;
    }

    @Override
    public List<ReservationDto> findAllByStatusRejected() {

        List<ReservationDto> reservations = reservationMapper.mapToDto(reservationRepository.findAllByStatus(EnumReservationStatus.REJECTED));

        for(ReservationDto reservation : reservations){
            reservation.setEndReservation(reservation.getEndReservation().minusMinutes(2));
        }

        return reservations;
    }

    @Override
    public List<ReservationDto> findAllByStatusCanceled() {

        List<ReservationDto> reservations = reservationMapper.mapToDto(reservationRepository.findAllByStatus(EnumReservationStatus.CANCELED));

        for(ReservationDto reservation : reservations){
            reservation.setEndReservation(reservation.getEndReservation().minusMinutes(2));
        }

        return reservations;
    }

    @Override
    public List<ReservationDto> findAllAcceptedReservationsByUser(String principalName) {

        List<ReservationDto> reservations = reservationMapper.mapToDto(reservationRepository.findByUserEmail(principalName));

        for(ReservationDto reservation : reservations){
            reservation.setEndReservation(reservation.getEndReservation().minusMinutes(2));
        }

        return reservations;
    }
}
