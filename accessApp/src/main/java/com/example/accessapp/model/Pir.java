package com.example.accessapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Pir {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pirId;
    private String motion;

    public void setMotion(String motion) {
        this.motion = motion;
    }
}
