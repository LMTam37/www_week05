package vn.edu.iuh.fit.week_05.services;

import vn.edu.iuh.fit.week_05.models.JobSkill;
import vn.edu.iuh.fit.week_05.models.pk.JobSkillPK;

import java.util.List;
import java.util.Optional;

public interface JobSkillService {
    JobSkill saveJobSkill(JobSkill jobSkill);

    Optional<JobSkill> getJobSkillById(JobSkillPK jobSkillPK);

    List<JobSkill> getAllJobSkills();

    void deleteJobSkill(JobSkillPK jobSkillPK);

    JobSkill updateJobSkill(JobSkillPK jobSkillPK, JobSkill updatedJobSkill);
}
