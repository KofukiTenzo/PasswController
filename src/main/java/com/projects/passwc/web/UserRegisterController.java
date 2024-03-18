package com.projects.passwc.web;

import com.projects.passwc.DTO.UserRegisterDTO;
import com.projects.passwc.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class UserRegisterController {
    private final UserService userService;

    public UserRegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userRegisterForm", new UserRegisterDTO());
        return "register_form";
    }

    @PostMapping("/register")
    public String processRegistration(@Valid UserRegisterDTO userRegisterDTO,
                                      Errors errors) throws IllegalStateException, IOException {
        if (errors.hasErrors()) return "register_form";

        if (userService.isValid(userRegisterDTO.getUsername())) //add return validation message

        userService.register(userRegisterDTO);

        return "redirect:/user/profile";
    }
}
