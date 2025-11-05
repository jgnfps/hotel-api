package com.project.hotel_api_p2.dto;

public record HotelResponseDTO(
        Long id,
        String nome,
        String endereco,
        String telefone,
        String email
) {}
