package com.sm.storagemanager.shared.exception;

import com.sm.storagemanager.businessentity.exception.BusinessEntityAlreadyExistsException;
import com.sm.storagemanager.businessentity.exception.ExceptionMessages;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import java.net.URI;
import java.time.OffsetDateTime;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessEntityAlreadyExistsException.class)
    public ProblemDetail handleBusinessEntityAlreadyExists(
            BusinessEntityAlreadyExistsException ex,
            HttpServletRequest request) {
        return buildProblemDetail(
                HttpStatus.CONFLICT,
                ExceptionMessages.ENTITY_ALREADY_EXIST.getMessage(),
                ex.getMessage(),
                request
        );
    }

    @ExceptionHandler({EntityNotFoundException.class, EmptyResultDataAccessException.class})
    public ProblemDetail handleNotFound(RuntimeException ex, HttpServletRequest request) {
        return buildProblemDetail(
                HttpStatus.NOT_FOUND,
                ExceptionMessages.ENTITY_ALREADY_EXIST.getMessage(),
                ex.getMessage(),
                request
        );
    }

    private ProblemDetail buildProblemDetail(
            HttpStatus status,
            String title,
            String detail,
            HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
        problemDetail.setTitle(title);
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        problemDetail.setProperty("timestamp", OffsetDateTime.now());
        return problemDetail;
    }
}
