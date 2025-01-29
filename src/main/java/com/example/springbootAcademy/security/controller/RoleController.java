package com.example.springbootAcademy.security.controller;

import com.example.springbootAcademy.security.entity.Role;
import com.example.springbootAcademy.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/create-new-role")
    public Role createNewRole(@RequestBody Role role) {
        return roleService.createnewRole(role);
    }

}
