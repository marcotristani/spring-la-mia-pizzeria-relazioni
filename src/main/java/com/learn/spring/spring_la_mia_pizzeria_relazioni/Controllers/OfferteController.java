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

import com.learn.spring.spring_la_mia_pizzeria_relazioni.models.Offerte;
import com.learn.spring.spring_la_mia_pizzeria_relazioni.repository.OfferteRepository;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/offers")
public class OfferteController {

    @Autowired
    private OfferteRepository offerteRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("offerte", offerteRepository.findAll());
        return "offers/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("offerta", new Offerte());
        return "offers/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Validated @ModelAttribute("offerta") Offerte formOfferta,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "offers/create-or-edit";
        }
        offerteRepository.save(formOfferta);
        return "redirect:/offers";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("offerta", offerteRepository.findById(id).get());
        model.addAttribute("edit", true);
        return "offers/create-or-edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@Validated @ModelAttribute("offerta") Offerte formOfferta,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            return "offers/create-or-edit";
        }
        offerteRepository.save(formOfferta);
        return "redirect:/offers";
    }

    @PostMapping("/{id}/delete")
    public String delete(Model model, @PathVariable("id") Integer id) {
        offerteRepository.deleteById(id);
        return "redirect:/offers";
    }

}
