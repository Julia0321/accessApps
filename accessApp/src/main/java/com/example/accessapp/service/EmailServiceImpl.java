package com.example.accessapp.service;

import com.example.accessapp.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl {

    private final JavaMailSender mailSender;

    public void sendAccessCode(String to, String accessCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("accessApp@gmail.com");
        message.setSubject("Your access code for the reservation");
        message.setText("The # character confirms the code. The * character deletes the last digit. Your access code is: " + accessCode);
        mailSender.send(message);
    }

    public void sendWarning(List<UserDto> admins, String warning) {
        SimpleMailMessage message = new SimpleMailMessage();
        String[] emails = admins.stream()
                .map(UserDto::getEmail)
                .filter(email -> email != null && email.contains("@"))
                .toArray(String[]::new);

        message.setTo(emails);
        message.setFrom("accessApp@gmail.com");
        message.setSubject("WARNING!!!");
        message.setText(warning);
        mailSender.send(message);
    }
}
