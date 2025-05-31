// src/main/java/br/com/breez/breez/controller/EmergencyController.java
package br.com.breez.breez.controller;

import br.com.breez.breez.service.EmergencyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmergencyController {

    private final EmergencyService emergencyService;

    public EmergencyController(EmergencyService emergencyService) {
        this.emergencyService = emergencyService;
    }

    @GetMapping("/emergency")
    public String emergencyPage(Model model) {
        model.addAttribute("contacts", emergencyService.getAllContacts());
        model.addAttribute("guide", emergencyService.getQuickGuide());
        model.addAttribute("helpNumber", emergencyService.getEmergencyNumber());
        return "emergency";
    }
}
