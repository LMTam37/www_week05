package vn.edu.iuh.fit.week_05.models.pk;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.iuh.fit.week_05.models.Job;
import vn.edu.iuh.fit.week_05.models.Skill;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobSkillPK implements Serializable {
    @JoinColumn(name = "job_id")
    @ManyToOne
    private Job job;
    @JoinColumn(name = "skill_id")
    @ManyToOne
    private Skill skill;
}
