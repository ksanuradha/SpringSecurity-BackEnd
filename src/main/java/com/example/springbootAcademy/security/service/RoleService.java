package com.example.springbootAcademy.security.service;

import com.example.springbootAcademy.security.entity.Role;
import com.example.springbootAcademy.security.repository.Rolerepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private Rolerepo rolerepo;

    public Role createnewRole(Role role) {
        return rolerepo.save(role);
    }
}
