package vn.edu.iuh.fit.week_05.services.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.week_05.models.JobSkill;
import vn.edu.iuh.fit.week_05.models.pk.JobSkillPK;
import vn.edu.iuh.fit.week_05.repositories.JobSkillRepository;
import vn.edu.iuh.fit.week_05.services.JobSkillService;

import java.util.List;
import java.util.Optional;

@Service
public class JobSkillServiceImpl implements JobSkillService {
    @Autowired
    private JobSkillRepository jobSkillRepository;

    @Override
    public JobSkill saveJobSkill(JobSkill jobSkill) {
        return jobSkillRepository.save(jobSkill);
    }

    @Override
    public Optional<JobSkill> getJobSkillById(JobSkillPK jobSkillPK) {
        return jobSkillRepository.findById(jobSkillPK);
    }

    @Override
    public List<JobSkill> getAllJobSkills() {
        return jobSkillRepository.findAll();
    }

    @Override
    public void deleteJobSkill(JobSkillPK jobSkillPK) {
        jobSkillRepository.deleteById(jobSkillPK);
    }

    @Override
    public JobSkill updateJobSkill(JobSkillPK jobSkillPK, JobSkill updatedJobSkill) {
        Optional<JobSkill> optionalJobSkill = jobSkillRepository.findById(jobSkillPK);

        if (optionalJobSkill.isPresent()) {
            JobSkill existingJobSkill = optionalJobSkill.get();
            existingJobSkill.setMoreInfos(updatedJobSkill.getMoreInfos());
            existingJobSkill.setSkillLevel(updatedJobSkill.getSkillLevel());

            return jobSkillRepository.save(existingJobSkill);
        } else {
            throw new EntityNotFoundException("JobSkill not found with id: " + jobSkillPK);
        }
    }
}
