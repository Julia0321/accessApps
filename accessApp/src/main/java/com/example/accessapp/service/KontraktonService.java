package com.example.accessapp.service;

import com.example.accessapp.model.Kontrakton;
import com.example.accessapp.repository.KontraktonRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KontraktonService {

    KontraktonRepository kontraktonRepository;

    KontraktonService(KontraktonRepository kontraktonRepository) {
        this.kontraktonRepository = kontraktonRepository;
    }

    public Kontrakton receiveKontraktonData(String doorState) {
        Kontrakton kontrakton = new Kontrakton();
        kontrakton.setDoorState(doorState);
        return kontraktonRepository.save(kontrakton);
    }

    public List<Kontrakton> kontraktonBaza() {
        return kontraktonRepository.findAll();
    }

    public ResponseEntity<Kontrakton> updateKontraktonData(Long id, Kontrakton kontrakton) {
        return kontraktonRepository.findById(id)
                .map(kontraktonFromDb -> {
                    kontraktonFromDb.setDoorState(kontrakton.getDoorState());
                    return ResponseEntity.ok().body(kontraktonRepository.save(kontraktonFromDb));
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
