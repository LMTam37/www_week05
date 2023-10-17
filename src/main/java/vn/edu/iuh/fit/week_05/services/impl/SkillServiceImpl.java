package vn.edu.iuh.fit.week_05.services.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.week_05.models.Skill;
import vn.edu.iuh.fit.week_05.repositories.SkillRepository;
import vn.edu.iuh.fit.week_05.services.SkillService;

import java.util.List;
import java.util.Optional;

@Service
public class SkillServiceImpl implements SkillService {
    @Autowired
    private SkillRepository skillRepository;

    @Override
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @Override
    public Optional<Skill> getSkillById(Long id) {
        return skillRepository.findById(id);
    }

    @Override
    public Skill saveSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    @Override
    public void deleteSkillById(Long id) {
        skillRepository.deleteById(id);
    }

    @Override
    public Skill updateSkill(Long id, Skill updatedSkill) {
        Optional<Skill> optionalSkill = skillRepository.findById(id);

        if (optionalSkill.isPresent()) {
            Skill existingSkill = optionalSkill.get();

            if (updatedSkill.getSkillDescription() != null && !updatedSkill.getSkillDescription().isEmpty()) {
                existingSkill.setSkillDescription(updatedSkill.getSkillDescription());
            }

            if (updatedSkill.getSkillName() != null && !updatedSkill.getSkillName().isEmpty()) {
                existingSkill.setSkillName(updatedSkill.getSkillName());
            }

            if (updatedSkill.getSkillType() != null) {
                existingSkill.setSkillType(updatedSkill.getSkillType());
            }

            return skillRepository.save(existingSkill);
        } else {
            throw new EntityNotFoundException("Skill not found with id: " + id);
        }
    }
}
