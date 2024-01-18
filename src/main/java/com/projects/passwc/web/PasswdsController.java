package com.projects.passwc.web;

import com.projects.passwc.DAO.Passwds;
import com.projects.passwc.data.PasswdsRepository;
import com.projects.passwc.data.PasswdsResponse;
import com.projects.passwc.forms.PasswdForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;


@Controller
@RequestMapping("/passwds")
public class PasswdsController {

    private final PasswdsRepository passwdsRepository;

    @Autowired
    public PasswdsController(PasswdsRepository passwdsRepository) {
        this.passwdsRepository = passwdsRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public PasswdsResponse passwds(
            @RequestParam(value = "page", defaultValue = "0") int pageNumber,
            Principal principal) {
        return passwdsRepository.findRecent(principal.getName(), pageNumber);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public String search(
            @PathVariable("name") String name,
            Model model,
            Principal principal) {
        model.addAttribute(passwdsRepository.findByName(principal.getName(), name));
        return "passwd";
    } //rewrite to search

    @GetMapping("/addPasswd")
    public String showPasswordForm(Model model) {
        model.addAttribute("passwdForm", new PasswdForm());
        return "passwdForm";
    }

    @GetMapping("/generatePassword")
    @ResponseBody
    public String generatePassword(
            @ModelAttribute PasswdForm passwdForm,
            @RequestParam(name = "useLower", required = false, defaultValue = "false") boolean useLower,
            @RequestParam(name = "useUpper", required = false, defaultValue = "false") boolean useUpper,
            @RequestParam(name = "useDigits", required = false, defaultValue = "false") boolean useDigits,
            @RequestParam(name = "usePunctuation", required = false, defaultValue = "false") boolean usePunctuation,
            @RequestParam(name = "length", required = false, defaultValue = "0") int length) {

        return passwdForm.generatePasswd(useLower, useUpper, useDigits, usePunctuation, length);
    }

    @PostMapping("/addPasswd")
    public String savePasswd(@Valid PasswdForm passwdForm,
                             Errors errors,
                             Principal principal) throws IllegalStateException, IOException {

        String username = principal.getName();
        Passwds passwds;

        if (errors.hasErrors())
            return "passwdForm";

        passwds = passwdForm.toPasswds(username);
        passwdsRepository.save(passwds);
        return "redirect:/passwds";
    }


}
