package com.projects.passwc.web;

import com.projects.passwc.Passwds;
import com.projects.passwc.data.PasswdsRepository;
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

    private PasswdsRepository passwdsRepository;

    @Autowired
    public PasswdsController(PasswdsRepository passwdsRepository) {
        this.passwdsRepository = passwdsRepository;
    }

//    @RequestMapping(method = RequestMethod.GET)
//    public String passwds(Model model) {
//        model.addAttribute("passwdList", passwcRepository.findPasswd(
//                Long.MAX_VALUE, 20));
//        return "passwds";
//    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Passwds> passwds(
            @RequestParam(value = "max", defaultValue = Long.MAX_VALUE + "") long max,
            @RequestParam(value = "count", defaultValue = "20") int count) {
        return passwdsRepository.findPasswds(max, count);
    }

//    @RequestMapping(value="/show", method = RequestMethod.GET)
//    public String showPasswd(@RequestParam("passwd_id") long passwdId,
//                             Model model){
//        model.addAttribute(passwcRepository.findOne(passwdId));
//        return "passwd";
//    }

    @RequestMapping(value = "/{passwdId}", method = RequestMethod.GET)
    public String passwd(@PathVariable("passwdId") long passwdId, Model model){
        model.addAttribute(passwdsRepository.findOne(passwdId));
        return "passwd";
    }
}
