package com.example.accessapp.model;

import com.example.accessapp.enums.EnumReservationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder(toBuilder = true)
@AllArgsConstructor
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "start_reservation")
    private LocalDateTime startReservation;

    @Column(name = "end_reservation")
    private LocalDateTime endReservation;


    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EnumReservationStatus status;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "access_data_id")
    private AccessData accessData;


    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "reservation_office", joinColumns = @JoinColumn(name = "reservation_id"), inverseJoinColumns = @JoinColumn(name = "office_id"))
    @ToString.Exclude
    private List<Office> offices;

    @Column(name = "reminder")
    private boolean codeMailSender = false;

    @Column(name = "warning")
    private boolean warningSender = false;

    @Column(name = "arduino_sender")
    private boolean arduinoSender = false;

    @Column(name = "default_sender")
    private boolean defaultSender = false;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "uuid", unique = true, nullable = false, updatable = false)
    private String uuid;

    public void addOffice(Office theOffice) {
        if (offices == null) {
            offices = new ArrayList<>();
        }

        offices.add(theOffice);
    }

    @PrePersist
    public void generateUuid() {
        if (uuid == null) {
            uuid = UUID.randomUUID().toString();
            status = EnumReservationStatus.PENDING;
        }
    }

}
