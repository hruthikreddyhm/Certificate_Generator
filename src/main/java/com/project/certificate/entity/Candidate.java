package com.project.certificate.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "candidates")
public class Candidate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Column(unique = true)
	private String email;
	private int score;
	private String courseName;
	private String organizationName;
	private String coordinatorName;

	
	@OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
	private List<Certificate> certificates;

	// getters & setters
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getScore() {
		return score;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setScore(int score) {
		this.score = score;
	}
	public String getCourseName() {
	    return courseName;
	}

	public void setCourseName(String courseName) {
	    this.courseName = courseName;
	}

	public String getOrganizationName() {
	    return organizationName;
	}

	public void setOrganizationName(String organizationName) {
	    this.organizationName = organizationName;
	}

	public String getCoordinatorName() {
	    return coordinatorName;
	}

	public void setCoordinatorName(String coordinatorName) {
	    this.coordinatorName = coordinatorName;
	}
}