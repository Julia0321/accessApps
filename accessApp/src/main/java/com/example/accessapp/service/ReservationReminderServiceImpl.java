package com.example.accessapp.service;

import com.example.accessapp.dto.ReservationDto;
import com.example.accessapp.mapper.ReservationMapper;
import com.example.accessapp.model.Reservation;
import com.example.accessapp.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationReminderServiceImpl {

    private final ReservationRepository reservationRepository;
    private final EmailServiceImpl emailService;
    private final ReservationMapper reservationMapper;

    @Scheduled(fixedRate = 60000)
    public void checkReservationsAndSendCodeToUser() {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime sendCodeTime = now.plusMinutes(2);

        List<Reservation> reservations = reservationRepository.findReservationsByStartDateBetween(now, sendCodeTime);

        for (Reservation reservation : reservations) {

            ReservationDto reservationDto = reservationMapper.mapToDto(reservation);
            System.out.println("-----------I am sending the mail with access code-----------");
            if (!reservation.isCodeMailSender()) {
                emailService.sendAccessCode(reservationDto.getUser().getEmail(), String.valueOf(reservationDto.getAccessData().getAccessCode()));
                reservation.setCodeMailSender(true);
                reservationRepository.save(reservation);
            }
        }


    }

}
