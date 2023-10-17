package vn.edu.iuh.fit.week_05.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.iuh.fit.week_05.enums.SkillType;

import java.util.List;

@Table(name = "skill")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String skillDescription;
    private String skillName;
    private SkillType skillType;
    @OneToMany(mappedBy = "jobSkillPK.skill")
    private List<JobSkill> jobSkills;
}
