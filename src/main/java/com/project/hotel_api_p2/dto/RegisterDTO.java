package com.project.hotel_api_p2.dto;

import com.project.hotel_api_p2.entity.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterDTO (
        @NotBlank(message = "O nome do usuário é obrigatório.")
        @Size(min = 3, max = 50, message = "O nome do usuário deve ter entre 3 e 50 caracteres.")
        String username,

        @NotBlank(message = "A senha é obrigatória.")
        @Size(min = 6, max = 100, message = "A senha deve ter pelo menos 6 caracteres.")
        String password,

        @NotNull(message = "A função (role) do usuário é obrigatória.")
        UserRole role,

        @NotBlank(message = "O email é obrigatório.")
        @Email(message = "O email informado não é válido.")
        String email
){
}
