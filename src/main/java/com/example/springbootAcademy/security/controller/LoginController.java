package com.example.springbootAcademy.security.controller;

import com.example.springbootAcademy.security.model.User;
import com.example.springbootAcademy.security.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@CrossOrigin
public class LoginController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        ResponseEntity response = null;
        try{
            String hashPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashPassword);
            user.setRole("ROLE_"+user.getRole());
            User savedUser = userRepo.save(user);
            if(savedUser.getId() > 0) {
                response = ResponseEntity
                            .status(HttpStatus.CREATED)
                            .body("Given User Details are Sucessfully Registered.");

            }
        } catch (Exception ex) {
            response = ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("An Exception occured due to " + ex.getMessage());
        }
        return response;
    }
}
