package vn.edu.iuh.fit.week_05.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.week_05.models.Candidate;
import vn.edu.iuh.fit.week_05.models.Job;
import vn.edu.iuh.fit.week_05.models.JobSkill;
import vn.edu.iuh.fit.week_05.models.Skill;
import vn.edu.iuh.fit.week_05.models.pk.JobSkillPK;
import vn.edu.iuh.fit.week_05.services.JobService;
import vn.edu.iuh.fit.week_05.services.JobSkillService;
import vn.edu.iuh.fit.week_05.services.SkillService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/job-skills")
public class JobSkillController {
    @Autowired
    private JobSkillService jobSkillService;
    @Autowired
    private JobService jobService;
    @Autowired
    private SkillService skillService;

    private Job getJobByIdOrNotFound(Long jobId) {
        return jobService.getJobById(jobId)
                .orElseThrow(() -> new EntityNotFoundException("Job not found"));
    }

    private Skill getSkillByIdOrNotFound(Long skillId) {
        return skillService.getSkillById(skillId)
                .orElseThrow(() -> new EntityNotFoundException("Skill not found"));
    }

    @GetMapping
    public List<JobSkill> getAllJobSkills() {
        return jobSkillService.getAllJobSkills();
    }

    @GetMapping("/{jobId}/{skillId}")
    public ResponseEntity<JobSkill> getJobSkillById(@PathVariable Long jobId, @PathVariable Long skillId) {
        try {
            Job job = getJobByIdOrNotFound(jobId);
            Skill skill = getSkillByIdOrNotFound(skillId);

            JobSkillPK jobSkillPK = new JobSkillPK(job, skill);
            Optional<JobSkill> jobSkill = jobSkillService.getJobSkillById(jobSkillPK);
            return jobSkill.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public JobSkill saveJobSkill(@RequestBody JobSkill jobSkill) {
        return jobSkillService.saveJobSkill(jobSkill);
    }

    @PutMapping("/{jobId}/{skillId}")
    public ResponseEntity<JobSkill> updateJobSkill(@PathVariable Long jobId, @PathVariable Long skillId, @RequestBody JobSkill updatedJobSkill) {
        try {
            Job job = getJobByIdOrNotFound(jobId);
            Skill skill = getSkillByIdOrNotFound(skillId);

            JobSkillPK jobSkillPK = new JobSkillPK(job, skill);
            JobSkill jobSkillUpdated = jobSkillService.updateJobSkill(jobSkillPK, updatedJobSkill);
            return ResponseEntity.ok(jobSkillUpdated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{jobId}/{skillId}")
    public ResponseEntity<Void> deleteJobSkill(@PathVariable Long jobId, @PathVariable Long skillId) {
        try {
            Job job = getJobByIdOrNotFound(jobId);
            Skill skill = getSkillByIdOrNotFound(skillId);

            JobSkillPK jobSkillPK = new JobSkillPK(job, skill);
            jobSkillService.deleteJobSkill(jobSkillPK);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
