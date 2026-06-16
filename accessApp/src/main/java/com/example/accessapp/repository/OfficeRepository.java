package com.example.accessapp.repository;

import com.example.accessapp.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OfficeRepository extends JpaRepository<Office, Integer> {

    Optional<Office> findByUuid(String uuid);

    @Query("SELECT DISTINCT o FROM Office o " +
            "JOIN FETCH o.building b " +
            "JOIN FETCH b.address " +
            "WHERE NOT EXISTS ( " +
            "SELECT r FROM Reservation r " +
            "JOIN r.offices office " +
            "WHERE office = o " +
            "AND r.startReservation <= :endReservation " +
            "AND r.endReservation >= :startReservation " +
            "AND (r.status = 'ACCEPTED'" +
            "OR r.status = 'PENDING'))")
    List<Office> findOfficesWithBuildingByAvailableReservations(@Param("startReservation") LocalDateTime startReservation, @Param("endReservation") LocalDateTime endReservation);
}
