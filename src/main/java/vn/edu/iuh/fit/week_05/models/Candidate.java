package vn.edu.iuh.fit.week_05.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Table(name = "candidate")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dob;
    private String email;
    private String full_name;
    @Column(length = 15)
    private String phone;
    @ManyToOne
    @JoinColumn(name = "address")
    private Address address;
}
