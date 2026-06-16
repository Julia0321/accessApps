package com.example.accessapp.controller;

import com.example.accessapp.service.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MailTestController {

    private final EmailServiceImpl emailService;

    public MailTestController(EmailServiceImpl emailService) {
        this.emailService = emailService;
    }

    @Value("${mail}")
    private String mail;

    @GetMapping("/send-test")
    public String sendTestMail() {
        emailService.sendAccessCode(mail, "TEST123");
        return "test-sending";
    }
}
