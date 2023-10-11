package vn.edu.iuh.fit.week_05.services;

import vn.edu.iuh.fit.week_05.models.Skill;

import java.util.List;
import java.util.Optional;

public interface SkillService {
    List<Skill> getAllSkills();
    Optional<Skill> getSkillById(Long id);
    Skill saveSkill(Skill skill);
    void deleteSkillById(Long id);
    Skill updateSkill(Long id, Skill updatedSkill);
}
