package br.com.breez.breez.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController {

    @Value("${tomtom.apikey}")
    private String tomTomApiKey;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("apikey", tomTomApiKey);
        return "home";
    }

}