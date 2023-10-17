package vn.edu.iuh.fit.week_05.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.week_05.models.Address;
import vn.edu.iuh.fit.week_05.models.Company;
import vn.edu.iuh.fit.week_05.models.Job;
import vn.edu.iuh.fit.week_05.services.CompanyService;
import vn.edu.iuh.fit.week_05.services.JobService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private JobService jobService;
    @Autowired
    private CompanyService companyService;
    @GetMapping
    public String listJobs(Model model) {
        List<Job> jobs = jobService.getAllJobs();
        model.addAttribute("jobs", jobs);
        return "jobs";
    }

    @GetMapping("/employerDashboard")
    public String showEmployerDashboard(Model model) {
        List<Company> companies = companyService.getAllCompanies();
        model.addAttribute("companies", companies);
        model.addAttribute("newCompany", new Company());
        model.addAttribute("newAddress", new Address());
        return "employerDashboard";
    }

    @GetMapping("/{id}")
    public String getJobById(@PathVariable Long id, Model model) {
        Optional<Job> job = jobService.getJobById(id);
        job.ifPresent(value -> model.addAttribute("job", value));
        return "jobs";
    }

    @PostMapping
    public String postJob(@ModelAttribute Job job) {
        jobService.saveJob(job);
        return "redirect:/jobs/employerDashboard";
    }


    @PutMapping
    public String updateJob(@ModelAttribute Job job) {
        jobService.updateJob(job.getId(), job);
        return "redirect:/jobs";
    }

    @DeleteMapping("/{id}")
    public String deleteJob(@PathVariable Long id) {
        jobService.deleteJobById(id);
        return "redirect:/jobs";
    }
}
