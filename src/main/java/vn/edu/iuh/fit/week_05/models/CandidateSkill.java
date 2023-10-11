package vn.edu.iuh.fit.week_05.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.iuh.fit.week_05.enums.SkillLevel;
import vn.edu.iuh.fit.week_05.models.pk.CandidateSkillPK;

@Table(name = "candidate_skill")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateSkill {
    @EmbeddedId
    private CandidateSkillPK candidateSkillPK;
    @Column(name = "more_infos", length = 1000)
    private String moreInfos;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "skill_level")
    private SkillLevel skillLevel;
}
