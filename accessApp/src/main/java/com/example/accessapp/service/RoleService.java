package com.example.accessapp.service;

import com.example.accessapp.dto.RoleDto;

import java.util.List;

public interface RoleService {
    List<RoleDto> findAll();

    RoleDto findDtoByUuid(String uuid);
}
