package vn.edu.iuh.fit.week_05.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
    private Long id;
    private Date fromDate;
    private Date toDate;
    private String companyName;
    private String role;
    private String workDescription;
    @ManyToOne
    private Candidate candidate;
}
