package com.project.hotel_api_p2.dto;

public record HospedeResponseDTO(
        Long id,
        String nomeCompleto,
        String cpf,
        String email,
        String telefone
) {}
