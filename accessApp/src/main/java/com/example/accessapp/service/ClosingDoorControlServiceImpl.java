package com.example.accessapp.service;

import com.example.accessapp.dto.ReservationDto;
import com.example.accessapp.dto.UserDto;
import com.example.accessapp.enums.EnumRole;
import com.example.accessapp.mapper.ReservationMapper;
import com.example.accessapp.mapper.UserMapper;
import com.example.accessapp.model.Kontrakton;
import com.example.accessapp.model.Pir;
import com.example.accessapp.model.Reservation;
import com.example.accessapp.model.Role;
import com.example.accessapp.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClosingDoorControlServiceImpl {

    private final PirRepository pirRepository;
    private final KontraktonRepository kontraktonRepository;
    private final ReservationRepository reservationRepository;
    private final EmailServiceImpl emailService;
    private final ReservationMapper reservationMapper;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Scheduled(fixedRate = 60000)
    public void checkDoorAndPirAfterReservation() {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime beforeOneMin = now.plusMinutes(1);

        List<Reservation> reservations = reservationRepository.findReservationsByEndDateAfterReservation(now, beforeOneMin);

        for (Reservation reservation : reservations) {

            Pir pir = pirRepository.findById(1L).orElseThrow(() -> new RuntimeException("Pir not found"));
            String pirStatus = pir.getMotion();
            Kontrakton kontrakton = kontraktonRepository.findById(1L).orElseThrow(() -> new RuntimeException("kontrakton not found"));
            String kontraktonStatus = kontrakton.getDoorState();

            if (!(kontraktonStatus.equals("false") && pirStatus.equals("false"))) {

                if (!reservation.isWarningSender()) {
                    ReservationDto reservationDto = reservationMapper.mapToDto(reservation);
                    Role admin = roleRepository.findByRoleName(EnumRole.ADMIN).orElseThrow(() -> new RuntimeException("Role not found"));
                    List<UserDto> admins = userMapper.mapToDto(userRepository.findUserByRole(admin));
                    String warning = "User does not close the door or is inside: " + reservationDto.getUser().getEmail();
                    System.out.println("--------------------I am sending the warning mail--------------------");
                    emailService.sendWarning(admins, warning);
                    reservation.setWarningSender(true);
                    reservationRepository.save(reservation);
                }
            }
        }

    }
}
