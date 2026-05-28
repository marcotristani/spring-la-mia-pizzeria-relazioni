package com.learn.spring.spring_la_mia_pizzeria_relazioni.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.spring.spring_la_mia_pizzeria_relazioni.models.Offerte;
import com.learn.spring.spring_la_mia_pizzeria_relazioni.repository.OfferteRepository;

@Service
public class OffertService {

    @Autowired
    private OfferteRepository offerteRepository;

    public List<Offerte> findAll() {
        return offerteRepository.findAll();
    }

    public Offerte getById(Integer id) {
        return offerteRepository.findById(id).get();
    }

    public Optional<Offerte> findById(Integer id) {
        return offerteRepository.findById(id);
    }

    public Offerte create(Offerte offerta) {
        return offerteRepository.save(offerta);
    }

    public Offerte edit(Offerte offerta) {
        return offerteRepository.save(offerta);
    }

    public void deleteById(Integer id) {
        offerteRepository.deleteById(id);
    }
}
