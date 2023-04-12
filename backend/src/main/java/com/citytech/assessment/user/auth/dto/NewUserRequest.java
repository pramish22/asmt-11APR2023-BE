package com.citytech.assessment.user.auth.dto;

import com.citytech.assessment.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewUserRequest {
    private String userId;

    private String name;

    private Role role;
}
