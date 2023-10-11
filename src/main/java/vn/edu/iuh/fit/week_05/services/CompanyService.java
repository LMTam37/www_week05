package vn.edu.iuh.fit.week_05.services;

import vn.edu.iuh.fit.week_05.models.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    List<Company> getAllCompanies();
    Optional<Company> getCompanyById(Long id);
    Company saveCompany(Company company);
    void deleteCompanyById(Long id);
    Company updateCompany(Long id, Company updatedCompany);
}
