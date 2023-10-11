package vn.edu.iuh.fit.week_05.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.week_05.models.Candidate;
import vn.edu.iuh.fit.week_05.models.CandidateSkill;
import vn.edu.iuh.fit.week_05.models.Skill;
import vn.edu.iuh.fit.week_05.models.pk.CandidateSkillPK;
import vn.edu.iuh.fit.week_05.services.CandidateService;
import vn.edu.iuh.fit.week_05.services.CandidateSkillService;
import vn.edu.iuh.fit.week_05.services.SkillService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/candidateskills")
public class CandidateSkillController {
    @Autowired
    private CandidateSkillService candidateSkillService;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private SkillService skillService;

    private Candidate getCandidateByIdOrNotFound(Long candidateId) {
        return candidateService.getCandidateById(candidateId)
                .orElseThrow(() -> new EntityNotFoundException("Candidate not found"));
    }

    private Skill getSkillByIdOrNotFound(Long skillId) {
        return skillService.getSkillById(skillId)
                .orElseThrow(() -> new EntityNotFoundException("Skill not found"));
    }

    @GetMapping
    public List<CandidateSkill> getAllCandidateSkills() {
        return candidateSkillService.getAllCandidateSkills();
    }

    @GetMapping("/{candidateId}/{skillId}")
    public ResponseEntity<CandidateSkill> getCandidateSkillById(@PathVariable Long candidateId, @PathVariable Long skillId) {
        try{
            Candidate candidate = getCandidateByIdOrNotFound(candidateId);
            Skill skill = getSkillByIdOrNotFound(skillId);

            CandidateSkillPK candidateSkillPK = new CandidateSkillPK(skill, candidate);
            Optional<CandidateSkill> candidateSkill = candidateSkillService.getCandidateSkillById(candidateSkillPK);

            return candidateSkill.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public CandidateSkill saveCandidateSkill(@RequestBody CandidateSkill candidateSkill) {
        return candidateSkillService.saveCandidateSkill(candidateSkill);
    }

    @PutMapping
    public ResponseEntity<CandidateSkill> updateCandidateSkill(@RequestBody CandidateSkill candidateSkill) {
        CandidateSkill updatedCandidateSkill = candidateSkillService.updateCandidateSkill(candidateSkill);
        return ResponseEntity.ok(updatedCandidateSkill);
    }

    @DeleteMapping("/{candidateId}/{skillId}")
    public ResponseEntity<Void> deleteCandidateSkill(@PathVariable Long candidateId, @PathVariable Long skillId) {
        try
        {
            Candidate candidate = getCandidateByIdOrNotFound(candidateId);
            Skill skill = getSkillByIdOrNotFound(skillId);

            CandidateSkillPK candidateSkillPK = new CandidateSkillPK(skill, candidate);
            candidateSkillService.deleteCandidateSkill(candidateSkillPK);

            return ResponseEntity.ok().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
