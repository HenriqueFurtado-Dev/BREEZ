// src/main/java/br/com/breez/breez/controller/LoginController.java
package br.com.breez.breez.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "login";  // vai renderizar src/main/resources/templates/login.html
    }
}
