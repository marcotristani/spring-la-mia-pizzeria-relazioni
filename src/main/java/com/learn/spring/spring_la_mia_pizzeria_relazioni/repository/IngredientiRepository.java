package com.learn.spring.spring_la_mia_pizzeria_relazioni.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.spring.spring_la_mia_pizzeria_relazioni.models.Ingrediente;

public interface IngredientiRepository extends JpaRepository<Ingrediente, Integer> {

}
