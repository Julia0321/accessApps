package com.example.accessapp.controller;

import com.example.accessapp.dto.ReservationDto;
import com.example.accessapp.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manageReservation")
public class ManageReservationController {

    private final ReservationService reservationService;

    @GetMapping
    public String showManagementReservationPage(Model theModel) {

        List<ReservationDto> theReservations = reservationService.findAllByStatusPending();

        theModel.addAttribute("reservations", theReservations);

        return "manage-reservation";
    }

    @GetMapping("/accepted")
    public String showManagementReservationPageStatusAccepted(Model theModel) {

        List<ReservationDto> theReservations = reservationService.findAllByStatusAccepted();

        theModel.addAttribute("reservations", theReservations);

        return "accepted";
    }

    @GetMapping("/rejected")
    public String showManagementReservationPageStatusRejected(Model theModel) {

        List<ReservationDto> theReservations = reservationService.findAllByStatusRejected();

        theModel.addAttribute("reservations", theReservations);

        return "rejected";
    }

    @GetMapping("/canceled")
    public String showManagementReservationPageStatusCanceled(Model theModel) {

        List<ReservationDto> theReservations = reservationService.findAllByStatusCanceled();

        theModel.addAttribute("reservations", theReservations);

        return "canceled";
    }


    @GetMapping("/{reservationUuid}/update")
    public String saveReservationAcceptStatus(@PathVariable String reservationUuid) {

        reservationService.updateStatus(reservationUuid);

        return "redirect:/manageReservation";
    }

    @GetMapping("/{reservationUuid}/reject")
    public String saveReservationRejectStatus(@PathVariable String reservationUuid) {

        reservationService.updateRejectedStatus(reservationUuid);

        return "redirect:/manageReservation";
    }

    @GetMapping("/{reservationUuid}/updateForAccept")
    public String saveReservationAcceptStatusForUpdate(@PathVariable String reservationUuid) {

        reservationService.updateStatus(reservationUuid);

        return "redirect:/manageReservation/rejected";
    }

    @GetMapping("/{reservationUuid}/updateForReject")
    public String saveReservationRejectStatusForUpdate(@PathVariable String reservationUuid) {

        reservationService.updateRejectedStatus(reservationUuid);

        return "redirect:/manageReservation/accepted";
    }
}
