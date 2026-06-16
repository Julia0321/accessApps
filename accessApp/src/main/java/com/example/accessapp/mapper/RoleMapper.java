package com.example.accessapp.mapper;

import com.example.accessapp.dto.RoleDto;
import com.example.accessapp.model.Role;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleMapper {

    public List<RoleDto> mapToDto(List<Role> roles) {
        return roles.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public RoleDto mapToDto(Role role) {
        return RoleDto.builder()
                .uuid(role.getUuid())
                .type(role.getType().getValue())
                .build();
    }
}
