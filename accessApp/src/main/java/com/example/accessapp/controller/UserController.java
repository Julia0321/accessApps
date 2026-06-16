package com.example.accessapp.controller;

import com.example.accessapp.dto.RoleDto;
import com.example.accessapp.dto.UserDto;
import com.example.accessapp.service.RoleService;
import com.example.accessapp.service.UserService;
import com.example.accessapp.validation.groups.CreateUserGroup;
import com.example.accessapp.validation.groups.UpdateUserGroup;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final RoleService roleService;


    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }


    @GetMapping("/add")
    public String showAddingForm(Model theModel) {

        theModel.addAttribute("userDto", new UserDto());
        /*theModel.addAttribute("allRoles", roleService.findAll());*/

        return "user-form";
    }


    @GetMapping("/list")
    public String showUsers(Model theModel) {

        List<UserDto> theUsers = userService.findAll();

        theModel.addAttribute("users", theUsers);

        return "list-users";
    }


    @PostMapping("/saveUser")
    public String saveUser(@Validated(CreateUserGroup.class) @ModelAttribute("userDto") UserDto theUser, Errors errors) {

        if (errors.hasErrors()) {
            System.out.println("Form has errors:");
            errors.getAllErrors().forEach(System.out::println);

            return "user-form";
        }
        userService.saveUser(theUser);

        return "redirect:/users/list";
    }
    @ModelAttribute("allRoles")
    public List<RoleDto> allRoles() {
        return roleService.findAll();
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("userUuid") String userUuid) {

        userService.deleteByUuid(userUuid);

        return "redirect:/users/list";
    }

    @GetMapping("/{userUuid}/update")
    public String updateForm(@PathVariable("userUuid") String userUuid, Model model) {
        UserDto user = userService.findByUuid(userUuid);
       /* List<RoleDto> allRoles = roleService.findAll();*/
        model.addAttribute("user", user);
        /*model.addAttribute("allRoles", allRoles);*/
        return "update-user";
    }

    @PostMapping("/{userUuid}/updateUser")
    public String updateUser(@Validated(UpdateUserGroup.class) @ModelAttribute("user") UserDto theUser, Errors errors) {

        if (errors.hasErrors()) {
            System.out.println("Form has errors:");
            errors.getAllErrors().forEach(System.out::println);

            return "update-user";
        }
        userService.updateUser(theUser);

        return "redirect:/users/list";
    }


}
