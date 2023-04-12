package com.citytech.assessment.user.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    private String token;

    private String tokenType = "Bearer";

    private String role;
}
