package com.example.accessapp.service;

import com.example.accessapp.model.Pir;

import java.util.List;

public interface PirService {
    Pir addPir(Pir pir);

    Pir updatePirData(Long pirId, Pir pir);

    List<Pir> getPirs();

    void deleteData(Long pirId);
}
