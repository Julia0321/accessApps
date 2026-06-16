package com.example.accessapp.mapper;

import com.example.accessapp.dto.AccessDataDto;
import com.example.accessapp.model.AccessData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AccessCodeMapper {

    ReservationMapper reservationMapper;

    public AccessDataDto mapToDto(AccessData accessData) {
        return AccessDataDto.builder()
                .reservation(reservationMapper.mapToDto(accessData.getReservation()))
                .uuid(accessData.getUuid())
                .build();
    }


    public String mapAccessCodeToString(AccessData accessData) {
        return String.valueOf(accessData.getAccessCode());
    }
}
