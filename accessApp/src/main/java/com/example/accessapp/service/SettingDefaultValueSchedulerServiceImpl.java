package com.example.accessapp.service;

import com.example.accessapp.model.Reservation;
import com.example.accessapp.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SettingDefaultValueSchedulerServiceImpl {

    private final ReservationRepository reservationRepository;
    private final AccessCodeSenderServiceImpl accessDataSenderServiceImpl;

    @Scheduled(fixedRate = 1000)
    public void checkReservation() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime from = now.plusSeconds(9);
        LocalDateTime to = now.plusSeconds(11);

        List<Reservation> reservations = reservationRepository.findReservationsByEndDateAfterReservation(from, to);

        for (Reservation reservation : reservations) {
            String accessCode = "!@#$";
            System.out.println("-------------------I am setting default code-------------------");
            accessDataSenderServiceImpl.sendCodeToArduino(accessCode);
            reservation.setDefaultSender(true);
            reservation.setAccessData(null);
            reservationRepository.save(reservation);
        }
    }
}
