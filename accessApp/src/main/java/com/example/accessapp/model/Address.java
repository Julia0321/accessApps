package com.example.accessapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "building_address")
    private String buildingAddress;

    @OneToOne(mappedBy = "address", cascade = {CascadeType.ALL})
    private Building building;

    @Column(name = "uuid", unique = true, nullable = false, updatable = false)
    private String uuid;

    @PrePersist
    public void generateUuid() {
        if (uuid == null) {
            uuid = UUID.randomUUID().toString();
        }
    }
}
