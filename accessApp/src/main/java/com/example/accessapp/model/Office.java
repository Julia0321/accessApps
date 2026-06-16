package com.example.accessapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "office")
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "num_of_office")
    private String numOfOffice;

    @Column(name = "description")
    private String description;


    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "offices")
    private List<Reservation> reservations;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "building_id")
    private Building building;
    @Column(name = "uuid", unique = true, nullable = false, updatable = false)
    private String uuid;

    public void addReservation(Reservation theReservation) {

        if (reservations == null) {
            reservations = new ArrayList<>();
        }

        reservations.add(theReservation);
        theReservation.addOffice(this);
    }

    @PrePersist
    public void generateUuid() {
        if (uuid == null) {
            uuid = UUID.randomUUID().toString();
        }
    }
}
