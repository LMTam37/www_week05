package vn.edu.iuh.fit.week_05.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "job")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Long jobId;
    @Column(name = "job_desc")
    private String jobDesc;
    @Column(name = "job_name")
    private String jobName;
    @ManyToOne
    @JoinColumn(name = "company")
    private Company company;
    @OneToMany
    private List<JobSkill> jobSkills;
}
