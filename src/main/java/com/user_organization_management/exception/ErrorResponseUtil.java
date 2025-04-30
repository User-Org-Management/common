package com.user_organization_management.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.user_organization_management.model.CustomErrorResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
public class ErrorResponseUtil {
    public static void writeErrorResponse(HttpServletResponse response, int status, String error, String message) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        CustomErrorResponse errorResponse = new CustomErrorResponse(
                status,
                error,
                message,
                LocalDateTime.now()
        );

        response.setStatus(status);
        response.setContentType("application/json");
        mapper.writeValue(response.getWriter(), errorResponse);
        response.getWriter().flush();
    }

    public static ResponseEntity<CustomErrorResponse> buildErrorResponse(HttpStatus status, String message) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                message,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, status);
    }

    public static void logAndBuildResponse(HttpStatus status, Exception ex) {
        if (status.is4xxClientError()) {
            log.warn("{}: {}", ex.getClass().getSimpleName(), ex.getMessage());
        } else {
            log.error("{}: {}", ex.getClass().getSimpleName(), ex.getMessage());
        }
    }
}
