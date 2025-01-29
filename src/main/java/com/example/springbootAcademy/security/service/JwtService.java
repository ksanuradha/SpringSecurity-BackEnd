package com.example.springbootAcademy.security.service;

import com.example.springbootAcademy.security.dto.LoginRequest;
import com.example.springbootAcademy.security.dto.LoginResponse;
import com.example.springbootAcademy.security.entity.User;
import com.example.springbootAcademy.security.repository.UserRepo;
import com.example.springbootAcademy.security.utill.JwtUtill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtill jwtUtill;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findById(username).get();
        if(user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getUserpassword(),
                    getAuthority(user)
            );
        } else {
            throw  new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    private Set getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        /**
        for (Role role: user.getRole()){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+ role.getRoleName()));
        }*/
        user.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+ role.getRoleName()));
        });
        return authorities;
    }

    public LoginResponse createJWTToken(LoginRequest loginRequest) throws Exception {
        String username = loginRequest.getUserName();
        String userPassword = loginRequest.getPassword();
        authenticate(username, userPassword);
        UserDetails userDetails = loadUserByUsername(username);
        String newGeneratedToken = jwtUtill.generateToken(userDetails);
        User user = userRepo.findById(username).get();
        LoginResponse loginResponse = new LoginResponse(
                user,
                newGeneratedToken
        );
        return loginResponse;
    }

    private void authenticate(String username, String userPassword) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,userPassword));
        } catch (BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


}
