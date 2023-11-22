package vn.edu.iuh.fit.week_05.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import vn.edu.iuh.fit.week_05.enums.SkillLevel;
import vn.edu.iuh.fit.week_05.models.pk.JobSkillPK;

@Table(name = "job_skill")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobSkill {
    @EmbeddedId
    private JobSkillPK jobSkillPK;
    @Column(name = "more_infos", length = 1000)
    private String moreInfos;
    @Column(name = "skill_level")
    @Enumerated(EnumType.ORDINAL)
    private SkillLevel skillLevel;
}
