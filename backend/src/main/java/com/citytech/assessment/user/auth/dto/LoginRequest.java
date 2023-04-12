package com.citytech.assessment.user.auth.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginRequest {
    @NotBlank
    private String userId;

    @NotBlank
    private String password;
}
