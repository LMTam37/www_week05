package vn.edu.iuh.fit.week_05.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.week_05.models.CandidateSkill;
import vn.edu.iuh.fit.week_05.models.pk.CandidateSkillPK;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateSkillRepository extends JpaRepository<CandidateSkill, CandidateSkillPK> {
    @Query("SELECT cs FROM CandidateSkill cs WHERE cs.candidateSkillPK.candidate.id = :candidateId")
    List<CandidateSkill> findByCandidateId(@Param("candidateId") Long candidateId);

    @Query("SELECT cs FROM CandidateSkill cs WHERE cs.candidateSkillPK.skill.id = :skillId")
    List<CandidateSkill> findBySkillId(@Param("skillId") Long skillId);

    Optional<CandidateSkill> findByCandidateSkillPK_Candidate_IdAndCandidateSkillPK_Skill_Id(Long candidateId, Long skillId);
}
