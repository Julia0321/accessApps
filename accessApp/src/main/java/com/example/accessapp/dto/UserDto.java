package com.example.accessapp.dto;

import com.example.accessapp.validation.UniqueEmail;
import com.example.accessapp.validation.groups.CreateUserGroup;
import com.example.accessapp.validation.groups.UpdateUserGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode()
@AllArgsConstructor
@Builder
public class UserDto {

    @EqualsAndHashCode.Include
    public String uuid;

    @NotBlank(message = "Email is required", groups = {CreateUserGroup.class, UpdateUserGroup.class})
    @Email(message = "Email must be properly formatted", groups = {CreateUserGroup.class, UpdateUserGroup.class})
    @UniqueEmail(groups = {CreateUserGroup.class})
    public String email;

    @NotBlank(message = "Password is required", groups = CreateUserGroup.class)
    @Size(min = 6, max = 15, message = "Password must be between 6 and 15 characters")
    public String password;

    @NotBlank(message = "Firstname is required", groups = {CreateUserGroup.class, UpdateUserGroup.class})
    public String firstName;

    @NotBlank(message = "Surname is required", groups = {CreateUserGroup.class, UpdateUserGroup.class})
    public String surname;

    public boolean active;

    @NotEmpty(message = "Role is required", groups = {CreateUserGroup.class, UpdateUserGroup.class})
    @Builder.Default
    public Set<RoleDto> roles = new HashSet<RoleDto>();
}
