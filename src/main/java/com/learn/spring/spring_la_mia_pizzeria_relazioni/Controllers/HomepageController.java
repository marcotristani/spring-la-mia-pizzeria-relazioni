package com.learn.spring.spring_la_mia_pizzeria_relazioni.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/homepage")
public class HomepageController {

    @GetMapping
    public String index(Model model) {
        return "/homepage";
    }
}
