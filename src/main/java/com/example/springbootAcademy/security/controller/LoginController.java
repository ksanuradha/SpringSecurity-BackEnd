package com.example.springbootAcademy.security.controller;

import com.example.springbootAcademy.security.dto.LoginRequest;
import com.example.springbootAcademy.security.dto.LoginResponse;
import com.example.springbootAcademy.security.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private JwtService jwtService;

    @PostMapping("/authenticate")
    public LoginResponse createJWTTokenAndLogin(@RequestBody LoginRequest loginRequest) throws Exception {
        return jwtService.createJWTToken(loginRequest);
    }
}
