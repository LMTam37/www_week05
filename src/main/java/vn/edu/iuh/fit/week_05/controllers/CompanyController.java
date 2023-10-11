package vn.edu.iuh.fit.week_05.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.week_05.models.Company;
import vn.edu.iuh.fit.week_05.services.CompanyService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Optional<Company> company = companyService.getCompanyById(id);
        return company.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Company saveCompany(@RequestBody Company company) {
        return companyService.saveCompany(company);
    }

    @PutMapping
    public ResponseEntity<Company> updateCompany(@RequestBody Company company) {
        try {
            Company companyUpdated = companyService.updateCompany(company.getCompanyId(), company);
            return ResponseEntity.ok(companyUpdated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        try {
            companyService.deleteCompanyById(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
