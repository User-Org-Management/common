package com.user_organization_management.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.user_organization_management.model.CustomErrorResponse;

import jakarta.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleEntityNotFound(EntityNotFoundException ex) {
        return logAndBuild(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<CustomErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
        return logAndBuild(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CustomErrorResponse> handleConstraintViolation(ConstraintViolationException ex) {
        List<String> errors = ex.getConstraintViolations()
                .stream()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .collect(Collectors.toList());

        logger.warn("Validation error (ConstraintViolationException): {}", errors);
        return buildErrorResponse(HttpStatus.BAD_REQUEST, String.join(", ", errors));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        logger.warn("Validation failed (MethodArgumentNotValidException): {}", errors);
        return buildErrorResponse(HttpStatus.BAD_REQUEST, String.join(", ", errors));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<CustomErrorResponse> handleIllegalState(IllegalStateException ex) {
        return logAndBuild(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(BadCredentialsAuthException.class)
    public ResponseEntity<CustomErrorResponse> handleBadCredentials(BadCredentialsAuthException ex) {
        return logAndBuild(HttpStatus.UNAUTHORIZED, ex);
    }

    @ExceptionHandler(AccountLockedException.class)
    public ResponseEntity<CustomErrorResponse> handleAccountLockedException(AccountLockedException ex){
        return logAndBuild(HttpStatus.LOCKED, ex);
    }


    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<CustomErrorResponse> handleAuthentication(AuthenticationException ex) {
        logger.error("Authentication error: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Authentication failed: " + ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<CustomErrorResponse> handleAccessDenied(AccessDeniedException ex) {
        logger.warn("Access denied: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.FORBIDDEN, "Access denied: " + ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CustomErrorResponse> handleRuntime(RuntimeException ex) {
        logger.error("Unexpected runtime exception", ex);
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred. Please try again later.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleGeneral(Exception ex) {
        logger.error("Unhandled exception", ex);
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong. Please contact support.");
    }


    private ResponseEntity<CustomErrorResponse> buildErrorResponse(HttpStatus status, String message) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                message,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, status);
    }

    private ResponseEntity<CustomErrorResponse> logAndBuild(HttpStatus status, Exception ex) {
        if (status.is4xxClientError()) {
            logger.warn("{}: {}", ex.getClass().getSimpleName(), ex.getMessage());
        } else {
            logger.error("{}: {}", ex.getClass().getSimpleName(), ex.getMessage());
        }
        return buildErrorResponse(status, ex.getMessage());
    }
}
