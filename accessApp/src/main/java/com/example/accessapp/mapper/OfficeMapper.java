package com.example.accessapp.mapper;

import com.example.accessapp.dto.OfficeDto;
import com.example.accessapp.model.Office;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class OfficeMapper {


    public List<OfficeDto> mapToDto(List<Office> offices) {

        return offices.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public OfficeDto mapToDto(Office theOffice) {

        return OfficeDto.builder()
                .numOfOffice(theOffice.getNumOfOffice())
                .building(theOffice.getBuilding())
                .uuid(theOffice.getUuid())
                .build();
    }

}
