package vn.edu.iuh.fit.week_05.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.week_05.models.JobSkill;
import vn.edu.iuh.fit.week_05.models.pk.JobSkillPK;

@Repository
public interface JobSkillRepository extends JpaRepository<JobSkill, JobSkillPK> {
}
