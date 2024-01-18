package com.projects.passwc.web;

import com.projects.passwc.DAO.User;
import com.projects.passwc.data.UserRegisterRepository;
import com.projects.passwc.forms.UserRegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class UserRegisterController {
    private UserRegisterRepository userRegisterRepository;

    @Autowired
    public UserRegisterController(UserRegisterRepository userRegisterRepository) {
        this.userRegisterRepository = userRegisterRepository;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userRegisterForm", new UserRegisterForm());
        return "registerForm";
    }

    @PostMapping("/register")
    public String processRegistration(@Valid UserRegisterForm userRegisterForm,
                                      Errors errors) throws IllegalStateException, IOException {
        if (errors.hasErrors())
            return "registerForm";

        User user = userRegisterForm.toUser();
        userRegisterRepository.save(user);
        return "redirect:/user/profile";
    }
}
