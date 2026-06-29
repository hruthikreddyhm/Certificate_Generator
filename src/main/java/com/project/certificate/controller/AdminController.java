package com.project.certificate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.project.certificate.dto.CandidateRequestDTO;
import com.project.certificate.entity.Candidate;
import com.project.certificate.services.CandidateService;

@RestController
@RequestMapping("/admin/candidates")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {

    @Autowired
    private CandidateService service;

    // CREATE CANDIDATE

    @PostMapping
    public ResponseEntity<Candidate> create(
            @RequestBody CandidateRequestDTO dto
    ) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createCandidate(dto));
    }

    // UPDATE CANDIDATE

    @PutMapping("/{id}")
    public ResponseEntity<Candidate> update(
            @PathVariable Long id,
            @RequestBody CandidateRequestDTO dto
    ) {

        return ResponseEntity.ok(
                service.updateCandidate(id, dto)
        );
    }

    // GET SINGLE CANDIDATE

    @GetMapping("/{id}")
    public ResponseEntity<Candidate> get(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
        		service.getCandidateById(id)
        );
    }

    // GET ALL CANDIDATES

    @GetMapping
    public ResponseEntity<List<Candidate>> getAll() {

        return ResponseEntity.ok(
                service.getAllCandidates()
        );
    }

    // DELETE CANDIDATE

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Long id
    ) {

        service.deleteCandidate(id);

        return ResponseEntity.ok(
                "Candidate deleted successfully"
        );
    }
}