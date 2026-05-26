package com.learn.spring.spring_la_mia_pizzeria_relazioni.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.learn.spring.spring_la_mia_pizzeria_relazioni.models.Offerte;
import com.learn.spring.spring_la_mia_pizzeria_relazioni.models.Pizza;

import com.learn.spring.spring_la_mia_pizzeria_relazioni.service.PizzaService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    // @Autowired
    // private PizzaRepository repositoryPizza;

    // @Autowired
    // private OfferteRepository repositoryOfferta;

    // @Autowired
    // private IngredientiRepository repositoryIngrediente;

    @Autowired
    private PizzaService servicePizza;

    @GetMapping
    public String index(Model model) {

        List<Pizza> pizzas = servicePizza.findAll();
        model.addAttribute("pizzas", pizzas);
        return "pizzas/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("pizza", servicePizza.getById(id));
        return "pizzas/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("ingredienti", servicePizza.findAll());
        return "pizzas/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredienti", servicePizza.findAll());
            return "pizzas/create-or-edit";
        }

        servicePizza.create(formPizza);
        return "redirect:/pizzas";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("pizza", servicePizza.getById(id));
        model.addAttribute("ingredienti", servicePizza.findAll());
        model.addAttribute("edit", true);
        return "pizzas/create-or-edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredienti", servicePizza.findAll());
            model.addAttribute("edit", true);
            return "pizzas/create-or-edit";
        }

        servicePizza.update(formPizza);
        return "redirect:/pizzas";
    }

    @PostMapping("/{id}/delete")
    public String delete(Model model, @PathVariable("id") Integer id) {

        servicePizza.delete(id);

        return "redirect:/pizzas";
    }

    @GetMapping("/{id}/offerta")
    public String addOfferta(@PathVariable("id") Integer id, Model model) {
        Offerte offerta = new Offerte();
        offerta.setPizza(servicePizza.getById(id));
        model.addAttribute("offerta", offerta);
        return "offers/create-or-edit";
    }

}
