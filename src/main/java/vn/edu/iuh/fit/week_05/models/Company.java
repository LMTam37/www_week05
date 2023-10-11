package vn.edu.iuh.fit.week_05.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "company")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comp_id")
    private Long companyId;
    @Column(length = 2000)
    private String about;
    private String email;
    @Column(name = "comp_name")
    private String companyName;
    private String phone;
    @Column(name = "web_url")
    private String webUrl;
    @ManyToOne
    @JoinColumn(name = "address")
    private Address address;
    @OneToMany(mappedBy = "company")
    private List<Job> jobs;
}
