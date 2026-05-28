package com.learn.spring.spring_la_mia_pizzeria_relazioni.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.spring.spring_la_mia_pizzeria_relazioni.models.Offerte;
import com.learn.spring.spring_la_mia_pizzeria_relazioni.models.Pizza;
import com.learn.spring.spring_la_mia_pizzeria_relazioni.repository.OfferteRepository;
import com.learn.spring.spring_la_mia_pizzeria_relazioni.repository.PizzaRepository;

@Service
public class PizzaService {

    @Autowired
    PizzaRepository repositoryPizza;

    @Autowired
    OfferteRepository repositoryOfferta;

    public List<Pizza> findAll() {
        List<Pizza> pizze = repositoryPizza.findAll();
        return pizze;
    }

    public Pizza getById(Integer id) {

        Pizza pizza = repositoryPizza.findById(id).get();
        return pizza;
    }

    public Optional<Pizza> findByid(Integer id) {
        return repositoryPizza.findById(id);
    }

    public Pizza create(Pizza pizza) {
        return repositoryPizza.save(pizza);
    }

    public Pizza update(Pizza pizza) {
        return repositoryPizza.save(pizza);
    }

    public void delete(Integer id) {
        Pizza pizzaDaEliminare = getById(id);
        for (Offerte offertaDaEliminare : pizzaDaEliminare.getOfferte()) {
            repositoryOfferta.delete(offertaDaEliminare);
        }
        repositoryPizza.delete(pizzaDaEliminare);
    }
}
