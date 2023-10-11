package vn.edu.iuh.fit.week_05.models.pk;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.iuh.fit.week_05.models.Candidate;
import vn.edu.iuh.fit.week_05.models.Skill;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateSkillPK implements Serializable {
    @JoinColumn(name = "skill_id")
    @ManyToOne
    private Skill skill;
    @JoinColumn(name = "can_id")
    @ManyToOne
    private Candidate candidate;
}
