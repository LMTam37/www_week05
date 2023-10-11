package vn.edu.iuh.fit.week_05.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.week_05.models.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
