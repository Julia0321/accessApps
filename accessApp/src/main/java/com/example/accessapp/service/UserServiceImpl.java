package com.example.accessapp.service;

import com.example.accessapp.dto.UserDto;
import com.example.accessapp.mapper.UserMapper;
import com.example.accessapp.model.Role;
import com.example.accessapp.model.User;
import com.example.accessapp.repository.RoleRepository;
import com.example.accessapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;


    @Override
    public List<UserDto> findAll() {
        return userMapper.mapToDto(userRepository.findAll());
    }


    @Override
    public UserDto saveUser(UserDto theUser) {
        User user = this.userMapper.mapToUser(theUser);

        theUser.getRoles().forEach(r -> {
            Role role = roleRepository.findByUuid(r.getUuid()).orElseThrow(() -> new RuntimeException("Role not found"));
            user.addRole(role);
        });
        return userMapper.mapToDto(userRepository.save(user));
    }


    @Override
    public UserDto findByUuid(String Uuid) {
        return userMapper.mapToDto(userRepository.findByUuid(Uuid));
    }

    @Override
    public void deleteByUuid(String userUuid) {
        User user = userRepository.findByUuid(userUuid).orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user);
    }


    @Override
    public UserDto updateUser(UserDto theUser) {

        Optional<User> userFromDb = userRepository.findByUuid(theUser.getUuid());
        User newUser = userFromDb.map(it -> {
            it.setFirstName(theUser.getFirstName());
            it.setSurname(theUser.getSurname());
            it.setEmail(theUser.getEmail());
            if (theUser.getPassword() != null) {
                it.setPassword(theUser.getPassword());
            }
            Set<Role> rolesToDelete = it.getRoles();
            it.getRoles().removeAll(rolesToDelete);
            theUser.getRoles().forEach(r -> {
                Role role = roleRepository.findByUuid(r.getUuid()).orElseThrow(() -> new RuntimeException("Role not found"));
                it.addRole(role);
            });
            return userRepository.save(it);
        }).orElseThrow(() -> new RuntimeException("User not found"));


        return userMapper.mapToDto(newUser);

    }

}
