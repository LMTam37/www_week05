package vn.edu.iuh.fit.week_05.services;

import vn.edu.iuh.fit.week_05.models.CandidateSkill;
import vn.edu.iuh.fit.week_05.models.pk.CandidateSkillPK;

import java.util.List;
import java.util.Optional;

public interface CandidateSkillService {
    CandidateSkill saveCandidateSkill(CandidateSkill candidateSkill);
    Optional<CandidateSkill> getCandidateSkillById(CandidateSkillPK candidateSkillPK);
    List<CandidateSkill> getAllCandidateSkills();
    List<CandidateSkill> getCandidateSkillsByCandidateId(Long candidateId);
    List<CandidateSkill> getCandidateSkillsBySkillId(Long skillId);
    CandidateSkill updateCandidateSkill(CandidateSkill candidateSkill);
    void deleteCandidateSkill(CandidateSkillPK candidateSkillPK);
}

