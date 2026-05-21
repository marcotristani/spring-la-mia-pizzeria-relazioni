package com.learn.spring.spring_la_mia_pizzeria_relazioni.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.learn.spring.spring_la_mia_pizzeria_relazioni.models.Pizza;
import com.learn.spring.spring_la_mia_pizzeria_relazioni.repository.PizzaRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaRepository repositoryPizza;

    @GetMapping
    public String index(Model model) {

        List<Pizza> pizzas = repositoryPizza.findAll();
        model.addAttribute("pizzas", pizzas);
        return "pizzas/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("pizza", repositoryPizza.findById(id).get());
        return "pizzas/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "pizzas/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "pizzas/create-or-edit";
        }

        repositoryPizza.save(formPizza);
        return "redirect:/pizzas";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("pizza", repositoryPizza.findById(id).get());
        model.addAttribute("edit", true);
        return "pizzas/create-or-edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            return "pizzas/create-or-edit";
        }

        repositoryPizza.save(formPizza);
        return "redirect:/pizzas";
    }

    @PostMapping("/{id}/delete")
    public String delete(Model model, @PathVariable("id") Integer id) {
        repositoryPizza.deleteById(id);
        return "redirect:/pizzas";
    }
}
