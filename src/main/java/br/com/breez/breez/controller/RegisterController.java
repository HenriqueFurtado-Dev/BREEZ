package br.com.breez.breez.controller;

import br.com.breez.breez.model.User;
import br.com.breez.breez.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(
            @Valid User user,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            return "register";
        }
        if (userService.emailExists(user.getEmail())) {
            model.addAttribute("emailError", "Já existe uma conta com esse e-mail");
            return "register";
        }
        userService.register(user);
        return "redirect:/login?registered";
    }
}
