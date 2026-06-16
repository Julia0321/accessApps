package com.example.accessapp.dto;

import com.example.accessapp.model.Address;
import com.example.accessapp.model.Office;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode()
public class BuildingDto {

    public String numOfBuilding;

    public Address address;

    public List<Office> offices;

    @EqualsAndHashCode.Include
    public String uuid;
}
