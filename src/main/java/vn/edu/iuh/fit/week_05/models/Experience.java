package vn.edu.iuh.fit.week_05.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
    public class Experience {
    @Id
    @Column(name = "exp_id")
    private Long id;
    private Date fromDate;
    private Date toDate;
    @Column(length = 120)
    private String companyName;
    @Column(length = 100)
    private String role;
    @Column(name = "work_desc", length = 400)
    private String workDescription;
    @ManyToOne
    @JoinColumn(name = "can_id")
    private Candidate candidate;
}
