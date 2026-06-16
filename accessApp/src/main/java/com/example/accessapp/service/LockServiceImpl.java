package com.example.accessapp.service;

import com.example.accessapp.exception.LockError;
import com.example.accessapp.exception.LockException;
import com.example.accessapp.model.Lock;
import com.example.accessapp.repository.LockRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LockServiceImpl implements LockService {

    private final LockRepository lockRepository;

    String response;

    private String URL = "http://172.20.10.5/";

    public LockServiceImpl(LockRepository ledRepository) {
        this.lockRepository = ledRepository;
    }

    public Lock addLock(Lock lock) {
        return lockRepository.save(lock);
    }

    public Lock toggleLock(Long lockId, Lock lock) {

        sendRequestToArduino(createAppropriateHeading(lock.isState(), lockId));

        return lockRepository.findById(lockId)
                .map(lockFromDb -> {
                    lockFromDb.setState(lock.isState());
                    return lockRepository.save(lockFromDb);
                }).orElseThrow(() -> new LockException(LockError.LOCK_NOT_FOUND));
    }


    public String createAppropriateHeading(Boolean lockState, Long lockId) {

        String headerForArduino;

        if (lockState) {
            headerForArduino = "ON/" + lockId;
        } else {
            headerForArduino = "OFF/" + lockId;
        }
        return headerForArduino;
    }

    public void sendRequestToArduino(String headerForArduino) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            response = restTemplate
                    .exchange(URL + headerForArduino,
                            HttpMethod.POST,
                            HttpEntity.EMPTY,
                            String.class)
                    .getBody();
        } catch (Exception e) {
            response = "Error communicating with Arduino: " + e.getMessage();
        }
    }
}

