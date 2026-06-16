package com.example.accessapp.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode()
public class SimpleBuildingDto {

    @EqualsAndHashCode.Include
    public String uuid;

    @ToString.Include
    public String numOfBuilding;

}
