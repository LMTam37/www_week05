package vn.edu.iuh.fit.week_05.services.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.week_05.models.Company;
import vn.edu.iuh.fit.week_05.repositories.CompanyRepository;
import vn.edu.iuh.fit.week_05.services.CompanyService;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Optional<Company> getCompanyById(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public void deleteCompanyById(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public Company updateCompany(Long id, Company updatedCompany) {
        Optional<Company> optionalCompany = companyRepository.findById(id);

        if (optionalCompany.isPresent()) {
            Company existingCompany = optionalCompany.get();

            if (updatedCompany.getAbout() != null && !updatedCompany.getAbout().isEmpty()) {
                existingCompany.setAbout(updatedCompany.getAbout());
            }

            if (updatedCompany.getEmail() != null && !updatedCompany.getEmail().isEmpty()) {
                existingCompany.setEmail(updatedCompany.getEmail());
            }

            if (updatedCompany.getCompanyName() != null && !updatedCompany.getCompanyName().isEmpty()) {
                existingCompany.setCompanyName(updatedCompany.getCompanyName());
            }

            if (updatedCompany.getPhone() != null && !updatedCompany.getPhone().isEmpty()) {
                existingCompany.setPhone(updatedCompany.getPhone());
            }

            if (updatedCompany.getWebUrl() != null && !updatedCompany.getWebUrl().isEmpty()) {
                existingCompany.setWebUrl(updatedCompany.getWebUrl());
            }

            if (updatedCompany.getAddress() != null) {
                existingCompany.setAddress(updatedCompany.getAddress());
            }

            return companyRepository.save(existingCompany);
        } else {
            throw new EntityNotFoundException("Company not found with id: " + id);
        }
    }
}
