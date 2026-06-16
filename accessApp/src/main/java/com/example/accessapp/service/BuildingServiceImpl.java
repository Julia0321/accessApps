package com.example.accessapp.service;

import com.example.accessapp.dto.BuildingDto;
import com.example.accessapp.mapper.BuildingMapper;
import com.example.accessapp.repository.BuildingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BuildingServiceImpl implements BuildingService {

    private final BuildingRepository buildingRepository;
    private final BuildingMapper buildingMapper;

    @Override
    public List<BuildingDto> findAll() {

        return buildingMapper.mapToDto(buildingRepository.findAll());
    }

}
