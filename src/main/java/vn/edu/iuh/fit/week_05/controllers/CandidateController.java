package vn.edu.iuh.fit.week_05.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.week_05.enums.SkillLevel;
import vn.edu.iuh.fit.week_05.models.Candidate;
import vn.edu.iuh.fit.week_05.models.CandidateSkill;
import vn.edu.iuh.fit.week_05.models.Skill;
import vn.edu.iuh.fit.week_05.models.pk.CandidateSkillPK;
import vn.edu.iuh.fit.week_05.services.CandidateService;
import vn.edu.iuh.fit.week_05.services.CandidateSkillService;
import vn.edu.iuh.fit.week_05.services.SkillService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    private CandidateSkillService candidateSkillService;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private SkillService skillService;

    private void prepareSkillOverview(Model model, Principal principal) {
        Optional<Candidate> candidate = candidateService.getCandidateByEmail(principal.getName());
        List<CandidateSkill> candidateSkills = candidateSkillService.getCandidateSkillsByCandidateId(candidate.get().getId());
        List<Skill> allSkills = skillService.getAllSkills();

        List<Skill> skillsToLearn = allSkills.stream()
                .filter(skill -> candidateSkills.stream()
                        .noneMatch(candidateSkill -> candidateSkill.getCandidateSkillPK().getSkill().equals(skill)))
                .collect(Collectors.toList());

        model.addAttribute("candidateSkills", candidateSkills);
        model.addAttribute("skillsToLearn", skillsToLearn);
    }

    @GetMapping
    public String showSkillsOverview(Model model, Principal principal) {
        prepareSkillOverview(model, principal);
        return "candidate/skillsOverview";
    }

    @PostMapping("/addSkill")
    public String addSkill(@RequestParam Long skillId, @RequestParam SkillLevel skillLevel, Model model, Principal principal) {
        Optional<Candidate> candidate = candidateService.getCandidateByEmail(principal.getName());

        if (candidate.isPresent()) {
            CandidateSkill candidateSkill = new CandidateSkill();
            CandidateSkillPK candidateSkillPK = new CandidateSkillPK();
            candidateSkillPK.setCandidate(candidate.get());

            Skill skill = new Skill();
            skill.setId(skillId);
            candidateSkillPK.setSkill(skill);

            candidateSkill.setCandidateSkillPK(candidateSkillPK);
            candidateSkill.setSkillLevel(skillLevel);

            candidateSkillService.saveCandidateSkill(candidateSkill);

        }
        return "redirect:/candidate";
    }

    @PostMapping("/removeSkill")
    public String removeSkill(@RequestParam Long skillId, Model model, Principal principal) {
        Optional<Candidate> candidate = candidateService.getCandidateByEmail(principal.getName());

        if (candidate.isPresent()) {
            Optional<CandidateSkill> candidateSkillToRemove = candidateSkillService.getCandidateSkillByCandidateAndSkillId(candidate.get().getId(), skillId);

            candidateSkillToRemove.ifPresent(candidateSkillService::deleteCandidateSkill);
        }

        return "redirect:/candidate";
    }
}
