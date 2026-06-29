package com.project.certificate.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "certificates")
public class Certificate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "candidate_id", nullable = false)
	private Candidate candidate;
	private boolean eligible;
	private String filePath;
	private LocalDateTime createdAt;

	@PrePersist
	public void prePersist() {
		this.createdAt = LocalDateTime.now();
	}

	// getters & setters
	public Long getId() {
		return id;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public void setEligible(boolean eligible) {
		this.eligible = eligible;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}