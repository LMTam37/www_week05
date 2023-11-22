package vn.edu.iuh.fit.week_05.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.week_05.models.CandidateSkill;
import vn.edu.iuh.fit.week_05.models.pk.CandidateSkillPK;
import vn.edu.iuh.fit.week_05.repositories.CandidateRepository;
import vn.edu.iuh.fit.week_05.repositories.CandidateSkillRepository;
import vn.edu.iuh.fit.week_05.services.CandidateSkillService;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateSkillImpl implements CandidateSkillService {
    @Autowired
    private CandidateSkillRepository candidateSkillRepository;

    @Override
    public CandidateSkill saveCandidateSkill(CandidateSkill candidateSkill) {
        return candidateSkillRepository.save(candidateSkill);
    }

    @Override
    public Optional<CandidateSkill> getCandidateSkillById(CandidateSkillPK candidateSkillPK) {
        return candidateSkillRepository.findById(candidateSkillPK);
    }

    @Override
    public List<CandidateSkill> getAllCandidateSkills() {
        return candidateSkillRepository.findAll();
    }

    @Override
    public List<CandidateSkill> getCandidateSkillsByCandidateId(Long candidateId) {
        return candidateSkillRepository.findByCandidateId(candidateId);
    }

    @Override
    public List<CandidateSkill> getCandidateSkillsBySkillId(Long skillId) {
        return candidateSkillRepository.findBySkillId(skillId);
    }

    @Override
    public CandidateSkill updateCandidateSkill(CandidateSkill candidateSkill) {
        return candidateSkillRepository.save(candidateSkill);
    }

    @Override
    public void deleteCandidateSkill(CandidateSkillPK candidateSkillPK) {
        candidateSkillRepository.deleteById(candidateSkillPK);
    }

    @Override
    public void deleteCandidateSkill(CandidateSkill candidateSkill) {
        candidateSkillRepository.delete(candidateSkill);
    }

    @Override
    public Optional<CandidateSkill> getCandidateSkillByCandidateAndSkillId(Long id, Long skillId) {
        return candidateSkillRepository.findByCandidateSkillPK_Candidate_IdAndCandidateSkillPK_Skill_Id(id, skillId);
    }
}
