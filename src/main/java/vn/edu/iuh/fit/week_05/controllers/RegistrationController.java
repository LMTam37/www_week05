package vn.edu.iuh.fit.week_05.controllers;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.iuh.fit.week_05.models.*;
import vn.edu.iuh.fit.week_05.services.SkillService;
import vn.edu.iuh.fit.week_05.services.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private UserService userService;
    @Autowired
    private SkillService skillService;
    @GetMapping
    public String showRegistrationForm(Model model) {
        Candidate candidate = new Candidate();
        candidate.setAddress(new Address());
        candidate.setSkillList(new ArrayList<>());
        candidate.setExperiences(new ArrayList<>());

        model.addAttribute("user", new User());

        model.addAttribute("candidate", candidate);

        model.addAttribute("company", new Company());

        model.addAttribute("skills", skillService.getAllSkills());
        model.addAttribute("countryCode", CountryCode.values());
        return "authorization/registration";
    }

    @PostMapping
    public String registerUserAccount(
            @ModelAttribute("newUser") User userRegistration,
            @ModelAttribute("newCandidate") Candidate candidate,
            @ModelAttribute("skillList") List<Skill> skillList){
        System.out.println("candidate = " + candidate);
        skillList.forEach(System.out::println);
//        userService.save(userRegistration);
        return "redirect:/registration?success";
    }
}
