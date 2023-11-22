package vn.edu.iuh.fit.week_05.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.week_05.enums.SkillLevel;
import vn.edu.iuh.fit.week_05.models.*;
import vn.edu.iuh.fit.week_05.models.pk.JobSkillPK;
import vn.edu.iuh.fit.week_05.repositories.SkillRepository;
import vn.edu.iuh.fit.week_05.services.CandidateService;
import vn.edu.iuh.fit.week_05.services.CompanyService;
import vn.edu.iuh.fit.week_05.services.JobService;
import vn.edu.iuh.fit.week_05.services.SkillService;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;
    private final SkillService skillService;
    private final CompanyService companyService;
    private final CandidateService candidateService;
    private final ObjectMapper objectMapper;

    @Autowired
    public JobController(JobService jobService, SkillService skillService, CompanyService companyService, CandidateService candidateService, ObjectMapper objectMapper) {
        this.jobService = jobService;
        this.skillService = skillService;
        this.companyService = companyService;
        this.candidateService = candidateService;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public String listJobs(Model model, Principal principal) {
        List<Job> jobs = jobService.getAllJobs();
        model.addAttribute("jobs", jobs);

        Authentication authentication = (Authentication) principal;
        boolean isEmployer = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_EMPLOYER"));

        boolean isCandidate = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_CANDIDATE"));

        model.addAttribute("isEmployer", isEmployer);
        model.addAttribute("isCandidate", isCandidate);
        return "jobs";
    }

    @GetMapping("/employerDashboard")
    public String showEmployerDashboard(Model model, Principal principal) {
        Optional<Company> currentCompany = companyService.getCurrentUserCompany(principal);
        prepareEmployerDashboard(model, currentCompany);
        findCandidatesForCompanyJobs(currentCompany.get());
        return "employer/employerDashboard";
    }

    @GetMapping("/{id}")
    public String getJobById(@PathVariable Long id, Model model) {
        Optional<Job> job = jobService.getJobById(id);
        job.ifPresent(value -> model.addAttribute("job", value));
        return "jobs";
    }

    @PostMapping("/postJob")
    public String postJob(@ModelAttribute Job job, @RequestParam String skillsNeed, Model model, Principal principal) {
        List<Map<String, Object>> selectedSkills = parseSelectedSkills(skillsNeed);
        List<JobSkill> jobSkills = createJobSkills(job, selectedSkills);

        Optional<Company> currentCompany = companyService.getCurrentUserCompany(principal);
        currentCompany.ifPresent(company -> {
            setUserCompany(job, company);
            job.setJobSkills(jobSkills);
            job.setCompany(company);
            jobService.saveJob(job);
        });

        prepareEmployerDashboard(model, currentCompany);

        return "employer/employerDashboard";
    }

    private List<Map<String, Object>> parseSelectedSkills(String skillsNeed) {
        try {
            JsonNode node = objectMapper.readTree(skillsNeed);

            return objectMapper.convertValue(node, List.class);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private List<Long> extractSkillIds(List<Map<String, Object>> selectedSkills) {
        return selectedSkills.stream()
                .map(skill -> Long.valueOf(skill.get("id").toString()))
                .collect(Collectors.toList());
    }

    private List<JobSkill> createJobSkills(Job job, List<Map<String, Object>> selectedSkills) {
        List<JobSkill> jobSkills = new ArrayList<>();

        selectedSkills.forEach(skill -> {
            Long skillId = Long.valueOf(String.valueOf(skill.get("id")));
            int skillLevelOrdinal = Integer.parseInt(skill.get("level").toString());
            SkillLevel skillLevel = SkillLevel.values()[skillLevelOrdinal];

            skillService.getSkillById(skillId).ifPresent(currentSkill -> {
                JobSkill newJobSkill = new JobSkill();
                newJobSkill.setJobSkillPK(new JobSkillPK(job, currentSkill));
                newJobSkill.setSkillLevel(skillLevel);
                jobSkills.add(newJobSkill);
            });
        });

        return jobSkills;
    }

    private void prepareEmployerDashboard(Model model, Optional<Company> currentCompany) {
        model.addAttribute("job", new Job());
        model.addAttribute("skills", skillService.getAllSkills());
        setUserCompanyAttribute(model, currentCompany);
    }

    private void setUserCompany(Job job, Company company) {
        job.setCompany(company);
    }

    private void setUserCompanyAttribute(Model model, Optional<Company> company) {
        company.ifPresent(currentCompany -> model.addAttribute("currentCompany", currentCompany));
    }

    public Set<Candidate> findCandidatesForCompanyJobs(Company company) {
        Set<Candidate> matchingCandidates = new HashSet<>();

        List<Job> companyJobs = jobService.getJobsByCompany(company);

        for (Job job : companyJobs) {
            List<JobSkill> jobSkills = job.getJobSkills();

            for (JobSkill jobSkill : jobSkills) {
                Skill skill = jobSkill.getJobSkillPK().getSkill();

                List<Candidate> candidatesWithSkill = candidateService.findBySkill(skill);

                matchingCandidates.addAll(candidatesWithSkill);
            }
        }

        return matchingCandidates;
    }

    @GetMapping("/candidates/{jobId}")
    public String showCandidatesForJob(@PathVariable Long jobId, Model model) {
        Optional<Job> job = jobService.getJobById(jobId);

        job.ifPresent(value -> {
            List<Candidate> candidates = findCandidatesForJob(value);
            model.addAttribute("job", value);
            model.addAttribute("candidates", candidates);
        });

        return "candidate/candidates";
    }

    private List<Candidate> findCandidatesForJob(Job job) {
        Set<Candidate> matchingCandidates = new HashSet<>();

        List<JobSkill> jobSkills = job.getJobSkills();

        for (JobSkill jobSkill : jobSkills) {
            Skill skill = jobSkill.getJobSkillPK().getSkill();
            List<Candidate> candidatesWithSkill = candidateService.findBySkill(skill);
            matchingCandidates.addAll(candidatesWithSkill);
        }

        return new ArrayList<>(matchingCandidates);
    }
}
