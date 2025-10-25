package com.project.hotel_api_p2.dto;

import com.project.hotel_api_p2.entity.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO (
        @NotBlank String username,
        @NotBlank String password,
        @NotBlank UserRole role,
        @Email String email
){}
