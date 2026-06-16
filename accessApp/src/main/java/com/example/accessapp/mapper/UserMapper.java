package com.example.accessapp.mapper;

import com.example.accessapp.dto.UserDto;
import com.example.accessapp.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class UserMapper {

    private final RoleMapper roleMapper;

    public List<UserDto> mapToDto(List<User> users) {
        return users.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public UserDto mapToDto(Optional<User> user) {
        return user.map(this::mapToDto).orElse(new UserDto());
    }

    public UserDto mapToDto(User user) {
        return UserDto.builder()
                .uuid(user.getUuid())
                .firstName(user.getFirstName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles().stream().map(roleMapper::mapToDto).collect(Collectors.toSet()))
                .build();
    }

    public User mapToUser(UserDto userDto) {
        return User.builder()
                .firstName(userDto.getFirstName())
                .surname(userDto.getSurname())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }
}
