package com.projects.passwc.web;

import com.projects.passwc.Entitys.User;
import com.projects.passwc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserProfileController {

    private UserService userService;

    @Autowired
    public UserProfileController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String showUserProfileForAuthenticatedUser(Principal principal, Model model) {
        String username = principal.getName();
        User user = userService.getAuthentication(username);
        model.addAttribute("user", user);
        return "profile";
    }
}
