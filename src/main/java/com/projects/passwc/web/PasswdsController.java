package com.projects.passwc.web;

import com.projects.passwc.entity.Passwds;
import com.projects.passwc.data.PasswdsRepository;
import com.projects.passwc.forms.PasswdForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/passwds")
public class PasswdsController {

    private final PasswdsRepository passwdsRepository;

    @Autowired
    public PasswdsController(PasswdsRepository passwdsRepository) {
        this.passwdsRepository = passwdsRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Passwds> passwds(
            @RequestParam(value = "max", defaultValue = Long.MAX_VALUE + "") long max,
            @RequestParam(value = "count", defaultValue = "20") int count) {
        return passwdsRepository.findPasswds(max, count);
    }

    @RequestMapping(value = "/{passwdId}", method = RequestMethod.GET)
    public String passwd(@PathVariable("passwdId") long passwdId, Model model){
        model.addAttribute(passwdsRepository.findOne(passwdId));
        return "passwd";
    }

    @RequestMapping(method=RequestMethod.POST)
    public String savePasswd(PasswdForm form, Model model) throws Exception {
        passwdsRepository.save(new Passwds(form.getResource_n(), form.getPasswd()));
        return "redirect:/passwds";
    }

}
