package com.example.accessapp.service;

import com.example.accessapp.dto.OfficeDto;
import com.example.accessapp.dto.SimpleBuildingDto;
import com.example.accessapp.dto.SimpleOfficeDto;
import com.example.accessapp.mapper.OfficeMapper;
import com.example.accessapp.model.Building;
import com.example.accessapp.model.Office;
import com.example.accessapp.repository.OfficeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OfficeServiceImpl implements OfficeService {

    private final OfficeRepository officeRepository;
    private final OfficeMapper officeMapper;

    @Override
    public List<OfficeDto> findAll() {

        return officeMapper.mapToDto(officeRepository.findAll());
    }

    @Override
    public HashMap<SimpleBuildingDto, List<SimpleOfficeDto>> getAvailable(LocalDateTime start, LocalDateTime end) {

        HashMap<SimpleBuildingDto, List<SimpleOfficeDto>> result = new HashMap<>();

        for (Office office : officeRepository.findOfficesWithBuildingByAvailableReservations(start, end)) {
            Building building = office.getBuilding();

            SimpleBuildingDto simpleBuilding = SimpleBuildingDto.builder()
                    .numOfBuilding(building.getNumOfBuilding())
                    .uuid(building.getUuid())
                    .build();

            if (result.get(simpleBuilding) == null) {
                result.put(simpleBuilding
                        , new ArrayList<SimpleOfficeDto>() {{
                            add(SimpleOfficeDto.builder()
                                    .uuid(office.getUuid())
                                    .numOfOffice(office.getNumOfOffice())
                                    .build());
                        }});
            } else {
                result.get(simpleBuilding).add(
                        SimpleOfficeDto.builder()
                                .uuid(office.getUuid())
                                .numOfOffice(office.getNumOfOffice())
                                .build());
            }


        }
        return result;
    }
}
