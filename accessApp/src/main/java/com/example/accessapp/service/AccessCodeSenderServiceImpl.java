package com.example.accessapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.HttpURLConnection;
import java.net.URL;

@Service
@RequiredArgsConstructor
public class AccessCodeSenderServiceImpl {

    private static final String IP = "172.20.10.5";
    private static final int PORT = 80;

    public void sendCodeToArduino(String code) {
        try {
            String url = UriComponentsBuilder.newInstance()
                    .scheme("http")
                    .host(IP)
                    .port(PORT)
                    .path("/accessCode")
                    .queryParam("value", code)
                    .build()
                    .toUriString();

            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            System.out.println("Sent to Arduino: " + code + " (response: " + responseCode + ")");
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
