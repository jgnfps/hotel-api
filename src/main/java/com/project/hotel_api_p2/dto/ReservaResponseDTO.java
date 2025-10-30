package com.project.hotel_api_p2.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReservaResponseDTO (
        Long id,
        LocalDate dataCheckIn,
        LocalDate dataCheckOut,
        BigDecimal valorTotal,
        String statusReserva,
        String nomeHospede,
        String numeroQuarto

){
}
