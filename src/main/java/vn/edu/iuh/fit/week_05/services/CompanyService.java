package vn.edu.iuh.fit.week_05.services;

import vn.edu.iuh.fit.week_05.models.Company;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface CompanyService {
    List<Company> getAllCompanies();
    Optional<Company> getCompanyById(Long id);

    Optional<Company> getCurrentUserCompany(Principal principal);

    Optional<Company> getCompanyByEmail(String email);
    Company saveCompany(Company company);
    void deleteCompanyById(Long id);
    Company updateCompany(Long id, Company updatedCompany);
}
