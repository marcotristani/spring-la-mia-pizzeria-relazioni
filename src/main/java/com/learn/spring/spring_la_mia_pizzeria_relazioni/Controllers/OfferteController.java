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
import com.learn.spring.spring_la_mia_pizzeria_relazioni.service.OffertService;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/offers")
public class OfferteController {

    @Autowired
    private OffertService offertaService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("offerte", offertaService.findAll());
        return "offers/index";
    }

    // @GetMapping("/create")
    // public String create(Model model) {
    // model.addAttribute("offerta", new Offerte());
    // return "offers/create-or-edit";
    // }

    @PostMapping("/create")
    public String store(@Validated @ModelAttribute("offerta") Offerte formOfferta,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "offers/create-or-edit";
        }
        offertaService.create(formOfferta);
        return "redirect:/offers";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("offerta", offertaService.getById(id));
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
        offertaService.edit(formOfferta);
        return "redirect:/offers";
    }

    @PostMapping("/{id}/delete")
    public String delete(Model model, @PathVariable("id") Integer id) {
        offertaService.deleteById(id);
        return "redirect:/offers";
    }

}
