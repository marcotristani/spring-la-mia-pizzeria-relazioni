package com.learn.spring.spring_la_mia_pizzeria_relazioni.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.learn.spring.spring_la_mia_pizzeria_relazioni.models.Ingrediente;
import com.learn.spring.spring_la_mia_pizzeria_relazioni.repository.IngredientiRepository;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/ingredients")
public class IngredientiController {

    @Autowired
    private IngredientiRepository repositoryIngredienti;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("ingredienti", repositoryIngredienti.findAll());
        return "ingredients/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Ingrediente ingrediente = new Ingrediente();
        model.addAttribute("ingrediente", ingrediente);
        return "ingredients/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Validated @ModelAttribute("ingrediente") Ingrediente formIngrediente,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "ingredients/create-or-edit";
        }

        repositoryIngredienti.save(formIngrediente);
        return "redirect:/ingredients";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("ingrediente", repositoryIngredienti.findById(id).get());
        model.addAttribute("edit", true);
        return "ingredients/create-or-edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@Validated @ModelAttribute("ingrediente") Ingrediente formIngrediente,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            return "ingredients/create-or-edit";
        }

        repositoryIngredienti.save(formIngrediente);
        return "redirect:/ingredients";
    }

    @PostMapping("/{id}/delete")
    public String postMethodName(@PathVariable Integer id, Model model) {

        repositoryIngredienti.delete(repositoryIngredienti.findById(id).get());
        return "redirect:/ingredients";
    }

}
