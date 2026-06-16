package com.example.accessapp.service;

import com.example.accessapp.mapper.AccessCodeMapper;
import com.example.accessapp.model.Reservation;
import com.example.accessapp.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccessCodeSchedulerServiceImpl {

    private final ReservationRepository reservationRepository;
    private final AccessCodeSenderServiceImpl accessCodeSenderServiceImpl;
    private final AccessCodeMapper accessCodeMapper;

    @Scheduled(fixedRate = 1000)
    public void checkReservationTimeAndSendCodeToArduino() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime from = now.plusSeconds(9);
        LocalDateTime to = now.plusSeconds(11);

        List<Reservation> reservations = reservationRepository.findReservationByStartDate(from, to);

        for (Reservation reservation : reservations) {
            String accessCode = accessCodeMapper.mapAccessCodeToString(reservation.getAccessData());
            System.out.println("-------------------sending access code....-------------------");
            accessCodeSenderServiceImpl.sendCodeToArduino(accessCode);
            reservation.setArduinoSender(true);
            reservationRepository.save(reservation);
        }
    }
}
