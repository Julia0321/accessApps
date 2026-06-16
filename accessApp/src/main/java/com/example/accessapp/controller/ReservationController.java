package com.example.accessapp.controller;

import com.example.accessapp.dto.CreateReservationDto;
import com.example.accessapp.dto.SimpleBuildingDto;
import com.example.accessapp.dto.SimpleOfficeDto;
import com.example.accessapp.service.OfficeService;
import com.example.accessapp.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final OfficeService officeService;

    @GetMapping
    public String showReservationForm(Model theModel) {

        theModel.addAttribute("reservation", new CreateReservationDto());

        return "reservation";
    }

    @GetMapping(value = "/available-offices", produces = {"application/json"})
    @ResponseBody
    public Map<SimpleBuildingDto, List<SimpleOfficeDto>> getAvailableOffice(
            @RequestParam("startReservation") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam("endReservation") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {

        return officeService.getAvailable(start, end);
    }


    @PostMapping("/save")
    public String saveReservation(@Valid @ModelAttribute("reservation") CreateReservationDto theReservationDto, Errors errors, Principal principal) {

        if(errors.hasErrors()) {

            return "reservation";
        }
        reservationService.saveReservation(theReservationDto, principal.getName());

        return "reservation-confirm";
    }

    @GetMapping("/showMyReservations")
    public String showUserReservationsForm(Model theModel, Principal principal) {

        theModel.addAttribute("reservations", reservationService.findAllAcceptedReservationsByUser(principal.getName()));

        return "user-reservation";
    }

    @GetMapping("/{reservationUuid}/cancel")
    public String saveReservationAcceptStatus(@PathVariable String reservationUuid) {

        reservationService.updateCanceledStatus(reservationUuid);

        return "redirect:/reservations/showMyReservations";
    }

}
