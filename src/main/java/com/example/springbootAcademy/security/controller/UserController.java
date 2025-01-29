package com.example.springbootAcademy.security.controller;

import com.example.springbootAcademy.security.dto.SignupRequest;
import com.example.springbootAcademy.security.entity.User;
import com.example.springbootAcademy.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register-new-user")
    public User registerNewUser(@RequestBody SignupRequest signupRequest){
        return userService.registerNewUser(signupRequest);
    }

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @GetMapping("/for-admin")
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin() {
        return "This url is only assessible to admin";
    }

    @GetMapping("/for-user")
    @PreAuthorize("hasAnyRole('User','Admin')")
//    @PreAuthorize("hasAnyRole('User')")
    public String forUser() {
        return "This url is only assessible to user";
    }

}
