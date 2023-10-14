package vn.edu.iuh.fit.week_05.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 150)
    private String street;
    @Column(length = 50)
    private String city;
    private Short country;
    @Column(length = 20)
    private String number;
    @Column(length = 7)
    private String zipcode;
    @OneToOne
    private Company company;
}
