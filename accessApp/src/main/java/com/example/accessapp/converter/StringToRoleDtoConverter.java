package com.example.accessapp.converter;

import com.example.accessapp.dto.RoleDto;
import com.example.accessapp.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StringToRoleDtoConverter implements Converter<String, RoleDto> {

    private final RoleService roleService;

    @Override
    public RoleDto convert(String source) {
        if (source.isBlank()) {
            return null;
        }
        return roleService.findDtoByUuid(source);
    }
}
