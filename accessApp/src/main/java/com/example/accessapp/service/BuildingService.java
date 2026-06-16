package com.example.accessapp.service;

import com.example.accessapp.dto.BuildingDto;
import com.example.accessapp.dto.SimpleBuildingDto;
import com.example.accessapp.dto.SimpleOfficeDto;

import java.util.List;
import java.util.Map;


public interface BuildingService {

    List<BuildingDto> findAll();

}
