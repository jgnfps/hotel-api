package com.project.hotel_api_p2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record QuartoRequestDTO(

        @NotBlank(message = "O número do quarto é obrigatório")
        String numero,

        @NotBlank(message = "O tipo do quarto é obrigatório")
        String tipo,

        @NotNull(message = "O preço da diária é obrigatório")
        @Positive(message = "O preço da diária deve ser maior que zero")
        BigDecimal precoDiaria,

        @NotNull(message = "A disponibilidade deve ser informada")
        Boolean disponivel,

        @NotNull(message = "O ID do hotel é obrigatório")
        Long hotelId,

        @NotNull(message = "A quantidade de Hospedes deve ser informada")
        Integer capacidadeHospedes
) {}
