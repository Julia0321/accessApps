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
@ToString(exclude = {"reservation"})
@NoArgsConstructor
@Table(name = "access_data")
public class AccessData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "access_code")
    private int accessCode;

    @OneToOne(mappedBy = "accessData", cascade = CascadeType.ALL)
    private Reservation reservation;

    @Column(name = "uuid", unique = true, nullable = false, updatable = false)
    private String uuid;

    @PrePersist
    public void generateUuid() {
        if (uuid == null) {
            uuid = UUID.randomUUID().toString();
            accessCode = (int) (Math.random() * 9000) + 1000;
        }
    }
}
