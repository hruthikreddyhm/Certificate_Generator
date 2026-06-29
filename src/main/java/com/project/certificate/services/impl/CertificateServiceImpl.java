package com.project.certificate.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.certificate.entity.Certificate;
import com.project.certificate.entity.Candidate;
import com.project.certificate.exception.CandidateNotEligibleException;
import com.project.certificate.exception.CandidateNotFoundException;
import com.project.certificate.repository.CandidateRepository;
import com.project.certificate.repository.CertificateRepository;

import com.project.certificate.services.CertificateService;
import com.project.certificate.util.PdfGeneratorUtil;

@Service
public class CertificateServiceImpl implements CertificateService {

    @Autowired
    private CandidateRepository userRepo;

    @Autowired
    private CertificateRepository certRepo;

    @Autowired
    private PdfGeneratorUtil pdfUtil;

    @Override
    public byte[] generateCertificate(String input) throws Exception {

        Candidate candidate;

        // CHECK INPUT TYPE

        if (input.matches("\\d+")) {

            Long candidateId = Long.parseLong(input);

            candidate = userRepo.findById(candidateId)
                    .orElseThrow(() ->
                            new CandidateNotFoundException(
                                    "User not found"
                            ));

        } else {

            candidate = userRepo.findByEmail(input)
                    .orElseThrow(() ->
                            new CandidateNotFoundException(
                                    "Email not found"
                            ));
        }

        // ELIGIBILITY CHECK

        if (candidate.getScore() < 60) {

            throw new CandidateNotEligibleException(
                    "User not eligible"
            );
        }

        // CHECK EXISTING CERTIFICATE

        Certificate existingCert =
                certRepo.findTopByCandidate_IdOrderByCreatedAtDesc(
                        candidate.getId()
                );

        // RETURN EXISTING

        if (existingCert != null) {

            System.out.println(
                    "Returning existing certificate"
            );

            return pdfUtil.generate(candidate);
        }

        // GENERATE PDF

        byte[] pdf = pdfUtil.generate(candidate);

        // SAVE CERTIFICATE

        Certificate cert = new Certificate();

        cert.setCandidate(candidate);
        cert.setEligible(true);
        cert.setFilePath("generated");

        certRepo.save(cert);

        return pdf;
    }
}