package com.project.certificate.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // CANDIDATE NOT FOUND

    @ExceptionHandler(CandidateNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCandidateNotFound(
            CandidateNotFoundException ex
    ) {

        return new ResponseEntity<>(

                new ErrorResponse(
                        ex.getMessage(),
                        404
                ),

                HttpStatus.NOT_FOUND
        );
    }

    // CANDIDATE NOT ELIGIBLE

    @ExceptionHandler(CandidateNotEligibleException.class)
    public ResponseEntity<ErrorResponse> handleNotEligible(
            CandidateNotEligibleException ex
    ) {

        return new ResponseEntity<>(

                new ErrorResponse(
                        ex.getMessage(),
                        400
                ),

                HttpStatus.BAD_REQUEST
        );
    }

    // GENERAL EXCEPTION

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(
            Exception ex
    ) {

        ex.printStackTrace();

        return new ResponseEntity<>(

                new ErrorResponse(
                        ex.getMessage(),
                        500
                ),

                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}