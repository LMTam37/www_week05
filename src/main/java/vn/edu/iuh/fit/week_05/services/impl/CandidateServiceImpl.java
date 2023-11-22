package vn.edu.iuh.fit.week_05.services.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.week_05.models.Candidate;
import vn.edu.iuh.fit.week_05.models.Skill;
import vn.edu.iuh.fit.week_05.repositories.CandidateRepository;
import vn.edu.iuh.fit.week_05.services.CandidateService;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateServiceImpl implements CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public Optional<Candidate> getCandidateByEmail(String email) {
        return candidateRepository.findByEmail(email);
    }

    @Override
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    @Override
    public Optional<Candidate> getCandidateById(Long id) {
        return candidateRepository.findById(id);
    }

    @Override
    public Candidate saveCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    @Override
    public Candidate updateCandidate(Long id, Candidate updatedCandidate) {
        Optional<Candidate> optionalCandidate = candidateRepository.findById(id);

        if (optionalCandidate.isPresent()) {
            Candidate existingCandidate = optionalCandidate.get();

            if (updatedCandidate.getDob() != null) {
                existingCandidate.setDob(updatedCandidate.getDob());
            }

            if (updatedCandidate.getEmail() != null && !updatedCandidate.getEmail().isEmpty()) {
                existingCandidate.setEmail(updatedCandidate.getEmail());
            }

            if (updatedCandidate.getFull_name() != null && !updatedCandidate.getFull_name().isEmpty()) {
                existingCandidate.setFull_name(updatedCandidate.getFull_name());
            }

            if (updatedCandidate.getPhone() != null && !updatedCandidate.getPhone().isEmpty()) {
                existingCandidate.setPhone(updatedCandidate.getPhone());
            }

            if (updatedCandidate.getAddress() != null) {
                existingCandidate.setAddress(updatedCandidate.getAddress());
            }

            return candidateRepository.save(existingCandidate);
        } else {
            throw new EntityNotFoundException("Candidate not found with id: " + id);
        }
    }

    @Override
    public void deleteCandidate(Long id) {
        candidateRepository.deleteById(id);
    }

    @Override
    public List<Candidate> findBySkill(Skill skill) {
        return candidateRepository.findBySkill(skill);
    }
}
