package com.projects.passwc.web;

import com.projects.passwc.DTO.UserRegisterDTO;
import com.projects.passwc.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String showRegistrationForm(@ModelAttribute UserRegisterDTO userRegisterDTO, Model model) {
        model.addAttribute("userRegisterDTO", userRegisterDTO);
        return "register_form";
    }

    @PostMapping("/register")
    public String processRegistration(@Valid UserRegisterDTO userRegisterDTO,
                                      BindingResult bindingResult) throws IllegalStateException, IOException {

        if (userService.userExistByUsername(userRegisterDTO.getUsername())){
            bindingResult.addError(new FieldError("userRegisterDTO", "username",
                    "Username already exists"));
        }

        if (userService.userExistByEmail(userRegisterDTO.getEmail())){
            bindingResult.addError(new FieldError("userRegisterDTO", "email",
                    "Email address already exists"));
        }

        if (bindingResult.hasErrors()) return "register_form";

        userService.register(userRegisterDTO);

        return "redirect:/user/profile";
    }
}
