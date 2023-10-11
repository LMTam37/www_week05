package vn.edu.iuh.fit.week_05.services;

import vn.edu.iuh.fit.week_05.models.Candidate;

import java.util.List;
import java.util.Optional;

public interface CandidateService {
    List<Candidate> getAllCandidates();
    Optional<Candidate> getCandidateById(Long id);
    Candidate saveCandidate(Candidate candidate);
    Candidate updateCandidate(Long id, Candidate updatedCandidate);
    void deleteCandidate(Long id);
}
