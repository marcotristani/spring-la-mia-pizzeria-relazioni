package com.learn.spring.spring_la_mia_pizzeria_relazioni.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.spring.spring_la_mia_pizzeria_relazioni.models.Pizza;
import com.learn.spring.spring_la_mia_pizzeria_relazioni.repository.PizzaRepository;

@Service
public class PizzaService {

    @Autowired
    PizzaRepository repositoryPizza;

    private List<Pizza> findAll() {
        List<Pizza> pizze = repositoryPizza.findAll();
        return pizze;
    }
}
