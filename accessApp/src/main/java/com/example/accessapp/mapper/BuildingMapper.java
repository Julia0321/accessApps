package com.example.accessapp.mapper;

import com.example.accessapp.dto.BuildingDto;
import com.example.accessapp.model.Building;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class BuildingMapper {


    public List<BuildingDto> mapToDto(List<Building> buildings) {
        return buildings.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public BuildingDto mapToDto(Building theBuilding) {

        return BuildingDto.builder()
                .uuid(theBuilding.getUuid())
                .numOfBuilding(theBuilding.getNumOfBuilding())
                .offices(theBuilding.getOffices())
                .build();

    }
}
