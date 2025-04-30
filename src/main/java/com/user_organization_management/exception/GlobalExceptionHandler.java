package com.user_organization_management.exception;

import com.user_organization_management.model.CustomErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolationException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleEntityNotFound(EntityNotFoundException ex) {
        ErrorResponseUtil.logAndBuildResponse(HttpStatus.NOT_FOUND, ex);
        return ErrorResponseUtil.buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<CustomErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
        ErrorResponseUtil.logAndBuildResponse(HttpStatus.BAD_REQUEST, ex);
        return ErrorResponseUtil.buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(DuplicateRecordException.class)
    public ResponseEntity<CustomErrorResponse> handleIllegalArgument(DuplicateRecordException ex) {
        ErrorResponseUtil.logAndBuildResponse(HttpStatus.CONFLICT, ex);
        return ErrorResponseUtil.buildErrorResponse(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CustomErrorResponse> handleConstraintViolation(ConstraintViolationException ex) {
        String errors = ex.getConstraintViolations()
                .stream()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .collect(Collectors.joining(", "));
        log.warn("Validation error (ConstraintViolationException): {}", errors);
        return ErrorResponseUtil.buildErrorResponse(HttpStatus.BAD_REQUEST, errors);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<CustomErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
//        List<String> errors = new ArrayList<>();
//        ex.getBindingResult().getFieldErrors()
//                .forEach(error -> errors.add(error.getDefaultMessage()));
//
//        ex.getBindingResult().getGlobalErrors()
//                .forEach(error -> errors.add(error.getDefaultMessage()));
//
//        log.warn("Validation failed (MethodArgumentNotValidException): {}", errors);
//        return ErrorResponseUtil.buildErrorResponse(HttpStatus.BAD_REQUEST, String.join(", ", errors));
//    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<CustomErrorResponse> handleIllegalState(IllegalStateException ex) {
        ErrorResponseUtil.logAndBuildResponse(HttpStatus.BAD_REQUEST, ex);
        return ErrorResponseUtil.buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(BadCredentialsAuthException.class)
    public ResponseEntity<CustomErrorResponse> handleBadCredentials(BadCredentialsAuthException ex) {
        ErrorResponseUtil.logAndBuildResponse(HttpStatus.UNAUTHORIZED, ex);
        return ErrorResponseUtil.buildErrorResponse(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(AccountLockedException.class)
    public ResponseEntity<CustomErrorResponse> handleAccountLockedException(AccountLockedException ex) {
        ErrorResponseUtil.logAndBuildResponse(HttpStatus.LOCKED, ex);
        return ErrorResponseUtil.buildErrorResponse(HttpStatus.LOCKED, ex.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<CustomErrorResponse> handleAuthentication(AuthenticationException ex) {
        log.error("Authentication error: {}", ex.getMessage());
        return ErrorResponseUtil.buildErrorResponse(HttpStatus.UNAUTHORIZED, "Authentication failed: " + ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<CustomErrorResponse> handleAccessDenied(AccessDeniedException ex) {
        log.warn("Access denied: {}", ex.getMessage());
        return ErrorResponseUtil.buildErrorResponse(HttpStatus.FORBIDDEN, "Access denied: " + ex.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        log.warn("Validation failed (MethodArgumentNotValidException): {}", errors);

        CustomErrorResponse error = new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), "Invalid Formats for Some Fields" ,errors, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CustomErrorResponse> handleRuntime(RuntimeException ex) {
        log.error("Unexpected runtime exception", ex);
        return ErrorResponseUtil.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred. Please try again later.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleGeneral(Exception ex) {
        log.error("Unhandled exception", ex);
        return ErrorResponseUtil.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong. Please contact support.");
    }
}
