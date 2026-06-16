package com.example.accessapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AccessAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccessAppApplication.class, args);
    }

}
