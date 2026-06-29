package com.project.certificate.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.certificate.dto.CandidateRequestDTO;
import com.project.certificate.entity.Candidate;
import com.project.certificate.exception.CandidateNotFoundException;
import com.project.certificate.repository.CandidateRepository;
import com.project.certificate.services.CandidateService;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepository repo;
    
    @Override
    public Candidate createCandidate(CandidateRequestDTO dto) {
        Candidate candidate = new Candidate();
        candidate.setName(dto.getName());
        candidate.setEmail(dto.getEmail());
        candidate.setScore(dto.getScore());
        candidate.setCourseName(dto.getCourseName());
        candidate.setOrganizationName(dto.getOrganizationName());
        candidate.setCoordinatorName(dto.getCoordinatorName());
        return repo.save(candidate);
    }
    
    @Override
    public Candidate updateCandidate(Long id, CandidateRequestDTO dto) {
        Candidate candidate = repo.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException("Candidate not found"));

        candidate.setName(dto.getName());
        candidate.setEmail(dto.getEmail());
        candidate.setScore(dto.getScore());
        candidate.setCourseName(dto.getCourseName());
        candidate.setOrganizationName(dto.getOrganizationName());
        candidate.setCoordinatorName(dto.getCoordinatorName());
        return repo.save(candidate);
    }

    
    @Override
    public Candidate getCandidateById(Long id) {

        return repo.findById(id)

                .orElseThrow(() ->
                        new CandidateNotFoundException(
                                "Candidate not found"
                        ));
    }
    @Override
    public Candidate getCandidateByEmail(String email) {

        return repo.findByEmail(email)

                .orElseThrow(() ->
                        new CandidateNotFoundException(
                                "Email not found"
                        ));
    }
    @Override
    public void deleteCandidate(Long id) {

        Candidate candidate = repo.findById(id)
                .orElseThrow(() ->
                        new CandidateNotFoundException("Candidate not found"));

        repo.delete(candidate);
    }
  

    @Override
    public List<Candidate> getAllCandidates() {

        return repo.findAll();
    }

	
}