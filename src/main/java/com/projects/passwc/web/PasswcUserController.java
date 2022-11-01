package com.projects.passwc.web;

import com.projects.passwc.PasswcUser;
import com.projects.passwc.data.PasswcUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/user")
public class PasswcUserController {
    private PasswcUserRepository passwcUserRepository;

    @Autowired
    public PasswcUserController(PasswcUserRepository passwcUserRepository){
        this.passwcUserRepository = passwcUserRepository;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationForm(){
        return "registerForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistration(
            @Valid PasswcUser passwcUser,
            Errors errors) {
            if (errors.hasErrors()) {
                return "registerForm";
            }

        passwcUserRepository.save(passwcUser);

        return "redirect:/user/" + passwcUser.getUsername();
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String showUserProfile(
            @PathVariable String username, Model model) {
    PasswcUser passwcUser = passwcUserRepository.findByUsername(username);
    model.addAttribute(passwcUser);
    return "profile";
    }
}
