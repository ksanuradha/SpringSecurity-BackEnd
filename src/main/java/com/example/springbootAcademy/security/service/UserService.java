package com.example.springbootAcademy.security.service;

import com.example.springbootAcademy.security.dto.SignupRequest;
import com.example.springbootAcademy.security.entity.Role;
import com.example.springbootAcademy.security.entity.User;
import com.example.springbootAcademy.security.repository.Rolerepo;
import com.example.springbootAcademy.security.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private Rolerepo rolerepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerNewUser(SignupRequest signupRequest) {
        if(!userRepo.existsById(signupRequest.getUsername())) {
            User user = new User();
            user.setUserName(signupRequest.getUsername());
            user.setUserpassword(getEncodedPassword(signupRequest.getUserPassword()));
            user.setUserFirstName(signupRequest.getUserFirstName());
            user.setUserLastName(signupRequest.getUserLastName());

            Set<Role> adminRoles = new HashSet<>();
            if (signupRequest.getUserRole().equalsIgnoreCase("user")) {

                Role role = new Role();
                role.setRoleName(signupRequest.getUserRole());
                adminRoles.add(role);
            } else {
                throw new RuntimeException("no roles like this");
            }
            user.setRole(adminRoles);
            return userRepo.save(user);
        } else {
            throw new RuntimeException("Already registered this Email");
        }
    }

    public void initRoleAndUser() {
        Role adminRole = new Role();
        Role userRole = new Role();
        if(!rolerepo.existsById("Admin")) {
            adminRole.setRoleName("Admin");
            adminRole.setRoleDescription("Admin Role");
            rolerepo.save(adminRole);
        }
        if(!rolerepo.existsById("User")) {
            userRole.setRoleName("User");
            userRole.setRoleDescription("User Role");
            rolerepo.save(userRole);
        }
        if(!userRepo.existsById("admin123")){
            User user = new User();
            user.setUserName("admin123");
            user.setUserpassword(getEncodedPassword("892310912V"));
            user.setUserFirstName("Sasidnu");
            user.setUserLastName("Anuradha");
            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(adminRole);
            user.setRole(adminRoles);
            userRepo.save(user);
        }
        if(!userRepo.existsById("user123")){
            User user = new User();
            user.setUserName("user123");
            user.setUserpassword(getEncodedPassword("892310912V"));
            user.setUserFirstName("Thamali");
            user.setUserLastName("Perera");

            Set<Role> userRoles = new HashSet<>();
            userRoles.add(userRole);

            user.setRole(userRoles);
            userRepo.save(user);
        }

    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
