package com.project.hotel_api_p2.dto;

import java.math.BigDecimal;

public record QuartoResponseDTO (
        Long id,
        String numero,
        String tipo,
        BigDecimal precoDiaria,
        Boolean disponivel,
        String hotel,
        Integer capacidadeHospedes
){
}
