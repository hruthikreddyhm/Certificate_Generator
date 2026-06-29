package com.project.certificate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.project.certificate.entity.Candidate;
import com.project.certificate.services.CandidateService;
import com.project.certificate.services.CertificateService;

@RestController
@RequestMapping("/candidate")
@CrossOrigin(origins = "http://localhost:5173")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private CertificateService certificateService;

    // GET CANDIDATE DETAILS

    @GetMapping("/{id}")

    public ResponseEntity<Candidate> getCandidate(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                candidateService.getCandidateById(id)
        );
    }

    // DOWNLOAD CERTIFICATE

    @GetMapping("/certificates/{input}")

    public ResponseEntity<byte[]> downloadCertificate(
            @PathVariable String input
    ) throws Exception {

        byte[] pdf =
                certificateService.generateCertificate(input);

        return ResponseEntity.ok()

                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=certificate.pdf"
                )

                .contentType(MediaType.APPLICATION_PDF)

                .body(pdf);
    }
}