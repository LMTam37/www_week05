package vn.edu.iuh.fit.week_05.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.week_05.models.Candidate;
import vn.edu.iuh.fit.week_05.services.CandidateService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/candidates")
public class CandidateController {
    @Autowired
    private CandidateService candidateService;

    @GetMapping
    public List<Candidate> getAllCandidates() {
        return candidateService.getAllCandidates();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable Long id) {
        Optional<Candidate> candidate = candidateService.getCandidateById(id);
        return candidate.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Candidate saveCandidate(@RequestBody Candidate candidate) {
        return candidateService.saveCandidate(candidate);
    }

    @PutMapping()
    public ResponseEntity<Candidate> updateCandidate(@RequestBody Candidate candidate) {
        try {
            Candidate candidateUpdated = candidateService.updateCandidate(candidate.getId(), candidate);
            return ResponseEntity.ok(candidateUpdated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable Long id) {
        try {
            candidateService.deleteCandidate(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

