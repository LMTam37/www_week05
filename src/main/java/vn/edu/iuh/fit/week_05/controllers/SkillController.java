package vn.edu.iuh.fit.week_05.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.week_05.models.Skill;
import vn.edu.iuh.fit.week_05.services.SkillService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/skills")
public class SkillController {
    @Autowired
    private SkillService skillService;

    @GetMapping
    public List<Skill> getAllSkills() {
        return skillService.getAllSkills();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Skill> getSkillById(@PathVariable Long id) {
        Optional<Skill> skill = skillService.getSkillById(id);
        return skill.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Skill saveSkill(@RequestBody Skill skill) {
        return skillService.saveSkill(skill);
    }

    @PutMapping
    public ResponseEntity<Skill> updateSkill(@RequestBody Skill skill) {
        try {
            Skill skillUpdated = skillService.updateSkill(skill.getSkillId(), skill);
            return ResponseEntity.ok(skillUpdated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        try {
            skillService.deleteSkillById(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
