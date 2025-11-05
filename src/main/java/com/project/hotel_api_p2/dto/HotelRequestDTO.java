package com.project.hotel_api_p2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record HotelRequestDTO(

        @NotBlank(message = "O nome do hotel é obrigatório")
        @Size(max = 100, message = "O nome do hotel deve ter no máximo 100 caracteres")
        String nome,

        @NotBlank(message = "O endereço é obrigatório")
        String endereco,

        @NotBlank(message = "O telefone é obrigatório")
        @Size(min = 10, max = 15, message = "O telefone deve ter entre 10 e 15 caracteres")
        String telefone,

        @NotBlank(message = "O email é obrigatório")
        @Email(message = "Informe um email válido")
        String email
) {}
