package com.projects.passwc.web;

import com.projects.passwc.data.UserRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserProfileController {

    private UserRegisterRepository userRegisterRepository;

    @Autowired
    public UserProfileController(UserRegisterRepository userRegisterRepository) {
        this.userRegisterRepository = userRegisterRepository;
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
