package com.project.certificate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.project.certificate.entity.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
	Optional<Candidate> findByEmail(String email);
}