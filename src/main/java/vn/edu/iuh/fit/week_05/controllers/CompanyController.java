package vn.edu.iuh.fit.week_05.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.iuh.fit.week_05.models.Address;
import vn.edu.iuh.fit.week_05.models.Company;
import vn.edu.iuh.fit.week_05.services.CompanyService;

@Controller
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping("/add")
    public String addCompany(@ModelAttribute Company company, @ModelAttribute Address address) {
        company.getAddress().add(address);
        companyService.saveCompany(company);
        return "redirect:/employerDashboard";
    }
}
