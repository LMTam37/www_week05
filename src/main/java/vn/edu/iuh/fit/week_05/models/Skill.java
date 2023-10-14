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
    @Column(name = "skill_id")
    private Long skillId;
    private String skill_description;
    private String skill_name;
    private SkillType type;
    @OneToMany
    private List<JobSkill> jobSkills;
}
