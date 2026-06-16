package com.example.accessapp.repository;

import com.example.accessapp.enums.EnumReservationStatus;
import com.example.accessapp.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    Reservation findByUuid(String uuid);

    List<Reservation> findAllByStatus(EnumReservationStatus status);

    List<Reservation> findByUserEmail(String email);

    List<Reservation> findByUserEmailAndStatus(String email, EnumReservationStatus status);

    @Query("SELECT DISTINCT r FROM Reservation r " +
            "JOIN FETCH r.user " +
            "JOIN FETCH r.offices " +
            "JOIN FETCH r.accessData " +
            "WHERE r.startReservation BETWEEN :now AND :sendCodeTime")
    List<Reservation> findReservationsByStartDateBetween(
            @Param("now") LocalDateTime now,
            @Param("sendCodeTime") LocalDateTime sendCodeTime);


    @Query("SELECT r FROM Reservation r " +
            "JOIN FETCH r.user " +
            "JOIN FETCH r.offices " +
            "WHERE r.endReservation BETWEEN :now AND :beforeOneMin")
    List<Reservation> findReservationsByEndDateAfterReservation(
            @Param("now") LocalDateTime now, @Param("beforeOneMin") LocalDateTime beforeOneMin);

    @Query("SELECT r FROM Reservation r " +
            "JOIN FETCH r.user " +
            "JOIN FETCH r.offices " +
            "JOIN FETCH r.accessData " +
            "WHERE r.startReservation BETWEEN :from AND :to")
    List<Reservation> findReservationByStartDate(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);


}

