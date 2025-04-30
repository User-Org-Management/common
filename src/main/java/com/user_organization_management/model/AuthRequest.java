package com.user_organization_management.model;


import lombok.*;

@Getter @Setter 
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    private String email;
    private String password;
}