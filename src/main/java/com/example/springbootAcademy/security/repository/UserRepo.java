package com.example.springbootAcademy.security.repository;

import com.example.springbootAcademy.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    List<User> findAllByEmailEquals(String userName);
}
