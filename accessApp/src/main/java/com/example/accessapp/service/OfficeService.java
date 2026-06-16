package com.example.accessapp.service;

import com.example.accessapp.dto.OfficeDto;
import com.example.accessapp.dto.SimpleBuildingDto;
import com.example.accessapp.dto.SimpleOfficeDto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public interface OfficeService {

    List<OfficeDto> findAll();

    HashMap<SimpleBuildingDto, List<SimpleOfficeDto>> getAvailable(LocalDateTime start, LocalDateTime end);
}
