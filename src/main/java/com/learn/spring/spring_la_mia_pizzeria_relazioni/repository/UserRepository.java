package com.learn.spring.spring_la_mia_pizzeria_relazioni.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.spring.spring_la_mia_pizzeria_relazioni.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
