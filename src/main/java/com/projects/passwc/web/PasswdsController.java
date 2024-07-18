package com.projects.passwc.web;

import com.projects.passwc.DTO.PasswdsDTO;
import com.projects.passwc.response.PasswdsResponse;
import com.projects.passwc.service.PasswdsService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;


@Controller
@RequestMapping("/passwds")
public class PasswdsController {

    private final PasswdsService passwdsService;

    public PasswdsController(PasswdsService passwdsService) {
        this.passwdsService = passwdsService;
    }

    @GetMapping
    public String passwds(
            @RequestParam(value = "page", defaultValue = "0") int pageNumber,
            Authentication username, Model model) {

        PasswdsResponse response = passwdsService.getAllUserPasswds(pageNumber, username.getName());
        model.addAttribute("response", response);
        
        return "passwds";
    }

//    @GetMapping
//    public PasswdsResponse search(
//            @RequestParam(value = "page", defaultValue = "0") int pageNumber,
//            @RequestParam("query") String query,
//            Principal principal) {
//        return passwdsRepository.showPasswds(pageNumber, passwdsRepository.searchPasswds(principal.getName(), query));
//    }

    @GetMapping("/add")
    public String showPasswordForm(Model model) {
        model.addAttribute("passwdForm", new PasswdsDTO());
        return "passwdForm";
    }

    @GetMapping("/generatePassword")
    @ResponseBody
    public String generatePassword(
            @ModelAttribute PasswdsDTO passwdForm,
            @RequestParam(name = "useLower", required = false, defaultValue = "true") boolean useLower,
            @RequestParam(name = "useUpper", required = false, defaultValue = "false") boolean useUpper,
            @RequestParam(name = "useDigits", required = false, defaultValue = "false") boolean useDigits,
            @RequestParam(name = "usePunctuation", required = false, defaultValue = "false") boolean usePunctuation,
            @RequestParam(name = "length", required = false, defaultValue = "0") int length) {

        if (length > 5 && (useLower || useUpper || useDigits || usePunctuation))
            return passwdsService.generatePasswd(useLower, useUpper, useDigits, usePunctuation, length);
        return null;
    }

    @PostMapping("/add")
    public String savePasswd(@Valid PasswdsDTO passwdForm,
                             Errors errors,
                             Authentication username) throws IllegalStateException, IOException {

        if (errors.hasErrors())
            return "passwdForm";

        passwdsService.save(passwdForm, username.getName());

        return "redirect:/passwds";
    }
}
