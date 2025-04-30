package com.user_organization_management.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomErrorResponse {
    private int status;
    private String error;
    private String message;
    private List<String> details;
    private LocalDateTime timestamp;

    public CustomErrorResponse(int status ,String error, String message, LocalDateTime timestamp) {
        this.error = error;
        this.timestamp = timestamp;
        this.message = message;
        this.status = status;

    }
}
