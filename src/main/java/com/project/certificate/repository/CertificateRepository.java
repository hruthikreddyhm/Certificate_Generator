package com.project.certificate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.certificate.entity.Certificate;

public interface CertificateRepository
        extends JpaRepository<Certificate, Long> {

    Certificate findTopByCandidate_IdOrderByCreatedAtDesc(
            Long candidateId
    );
}