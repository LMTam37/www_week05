package vn.edu.iuh.fit.week_05.services;

import vn.edu.iuh.fit.week_05.models.Candidate;
import vn.edu.iuh.fit.week_05.models.Skill;

import java.util.List;
import java.util.Optional;

public interface CandidateService {
    Optional<Candidate> getCandidateByEmail(String email);
    List<Candidate> getAllCandidates();
    Optional<Candidate> getCandidateById(Long id);
    Candidate saveCandidate(Candidate candidate);
    Candidate updateCandidate(Long id, Candidate updatedCandidate);
    void deleteCandidate(Long id);
    List<Candidate> findBySkill(Skill skill);
}
