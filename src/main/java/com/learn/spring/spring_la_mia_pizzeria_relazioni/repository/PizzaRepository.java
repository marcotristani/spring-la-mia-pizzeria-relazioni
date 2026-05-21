package com.learn.spring.spring_la_mia_pizzeria_relazioni.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.spring.spring_la_mia_pizzeria_relazioni.models.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

}
