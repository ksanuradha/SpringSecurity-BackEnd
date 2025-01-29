package com.example.springbootAcademy.security.repository;

import com.example.springbootAcademy.security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface Rolerepo extends JpaRepository<Role, String> {
}
