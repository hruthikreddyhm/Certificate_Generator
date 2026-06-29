package com.project.certificate.services;

import java.util.List;

import org.jspecify.annotations.Nullable;

import com.project.certificate.dto.CandidateRequestDTO;
import com.project.certificate.entity.Candidate;

public interface CandidateService {

	Candidate createCandidate(CandidateRequestDTO dto);

	Candidate updateCandidate(Long id, CandidateRequestDTO dto);

	

	Candidate getCandidateById(Long id);

	Candidate getCandidateByEmail(String email);

	void deleteCandidate(Long id);
	
	List<Candidate> getAllCandidates();
}