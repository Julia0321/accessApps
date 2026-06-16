package com.example.accessapp.service;

import com.example.accessapp.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> findAll();

    void deleteByUuid(String userUuid);

    UserDto saveUser(UserDto theUser);

    UserDto findByUuid(String userUuid);

    UserDto updateUser(UserDto theUser);


}
