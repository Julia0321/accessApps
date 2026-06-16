package com.example.accessapp.service;

import com.example.accessapp.dto.RoleDto;
import com.example.accessapp.mapper.RoleMapper;
import com.example.accessapp.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public List<RoleDto> findAll() {
        return roleMapper.mapToDto(roleRepository.findAll());
    }

    @Override
    public RoleDto findDtoByUuid(String uuid) {
        return roleMapper.mapToDto(roleRepository.findByUuid(uuid).orElseThrow(() -> new RuntimeException("Role not found")));
    }


}
