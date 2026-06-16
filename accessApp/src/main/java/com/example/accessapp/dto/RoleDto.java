package com.example.accessapp.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode()
public class RoleDto {

    @EqualsAndHashCode.Include
    public String uuid;

    public String type;

}
