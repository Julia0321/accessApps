package com.example.accessapp.service;

import com.example.accessapp.exception.PirError;
import com.example.accessapp.exception.PirException;
import com.example.accessapp.model.Pir;
import com.example.accessapp.repository.PirRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PirServiceImpl implements PirService {
    private final PirRepository pirRepository;

    PirServiceImpl(PirRepository pirRepository) {
        this.pirRepository = pirRepository;
    }

    public Pir addPir(Pir pir) {
        return pirRepository.save(pir);
    }


    public Pir updatePirData(Long pirId, Pir pir) {
        return pirRepository.findByPirId(pirId)
                .map(pirFromDb -> {
                    pirFromDb.setMotion(pir.getMotion());
                    return pirRepository.save(pirFromDb);
                }).orElseThrow(() -> new PirException(PirError.PIR_NOT_FOUND));
    }

    public List<Pir> getPirs() {
        return pirRepository.findAll();
    }

    public void deleteData(Long pirId) {
        Pir pir = pirRepository.findByPirId(pirId)
                .orElseThrow(() -> new PirException(PirError.PIR_NOT_FOUND));
        pirRepository.deleteByPirId(pir);
    }

}
