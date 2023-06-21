package com.projects.passwc.web;

import com.projects.passwc.DAO.User;
import com.projects.passwc.data.UserRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserProfileController {

    private UserRegisterRepository userRegisterRepository;

    @Autowired
    public UserProfileController(UserRegisterRepository userRegisterRepository) {
        this.userRegisterRepository = userRegisterRepository;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String showUserProfileForAuthenticatedUser(Principal principal, Model model) {
        String username = principal.getName();
        User user = userRegisterRepository.findByUsername(username);
        model.addAttribute("user", user);
        return "profile";
    }
}
