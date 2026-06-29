package com.project.certificate.services;


public interface CertificateService {
	byte[] generateCertificate(String input) throws Exception;
}