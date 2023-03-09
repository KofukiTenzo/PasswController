package com.projects.passwc.web;

import com.projects.passwc.entity.User;
import com.projects.passwc.data.UserRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserRegisterController {
    private UserRegisterRepository userRegisterRepository;

    @Autowired
    public UserRegisterController(UserRegisterRepository userRegisterRepository) {
        this.userRegisterRepository = userRegisterRepository;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute(new User());
        return "registerForm";
    }

//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public String processRegistration(
//            @Valid User user,
//            Errors errors) {
//        if (errors.hasErrors()) {
//            return "registerForm";
//        }
//
//        return "redirect:/login";
//    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistration(@Valid UserRegisterForm userRegisterForm,
                                      Errors errors) throws IllegalStateException, IOException {
        if (errors.hasErrors())
            return "registerForm";

        User user = userRegisterForm.toUser();
        userRegisterRepository.save(user);
        return "redirect:/user/" + user.getUsername();
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String showUserProfile(@PathVariable String username,
                                 Model model) {
        if (!model.containsAttribute("user")) {
            model.addAttribute(
                    userRegisterRepository.findByUsername(username));
        }
        return "profile";
    }
}
