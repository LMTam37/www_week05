    package vn.edu.iuh.fit.week_05.models;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.util.Collection;

    @Entity
    @Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "user_id")
        private Long id;
        private String email;
        private String password;
        @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        @JoinTable(
                name = "users_roles",
                joinColumns = @JoinColumn(
                        name = "user_id", referencedColumnName = "user_id"),
                inverseJoinColumns = @JoinColumn(
                        name = "role_id", referencedColumnName = "role_id"
                )
        )
        private Collection<Role> roles;
    }
