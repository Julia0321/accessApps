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
@Table(name = "building")
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "num_of_building")
    private String numOfBuilding;


    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "building", cascade = {CascadeType.ALL})
    private List<Office> offices;
    @Column(name = "uuid", unique = true, nullable = false, updatable = false)
    private String uuid;

    public void addOffice(Office theOffice) {

        if (offices == null) {
            offices = new ArrayList<>();
        }

        offices.add(theOffice);
        theOffice.setBuilding(this);
    }

    @PrePersist
    public void generateUuid() {
        if (uuid == null) {
            uuid = UUID.randomUUID().toString();
        }
    }
}
