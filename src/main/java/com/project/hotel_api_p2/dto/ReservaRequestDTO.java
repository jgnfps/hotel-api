package com.project.hotel_api_p2.dto;

import com.project.hotel_api_p2.entity.enums.StatusReserva;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReservaRequestDTO(
        @NotNull(message = "A data de check-in é obrigatória.")
        @FutureOrPresent(message = "A data de check-in não pode ser no passado.")
        LocalDate dataCheckIn,

        @NotNull(message = "A data de check-out é obrigatória.")
        @Future(message = "A data de check-out deve ser no futuro.")
        LocalDate dataCheckOut,

        @NotNull(message = "O valor total é obrigatório.")
        @DecimalMin(value = "0.0", inclusive = false, message = "O valor total deve ser maior que zero.")
        BigDecimal valorTotal,

        @NotBlank(message = "O status da reserva é obrigatório.")
        String statusReserva,

        @NotNull(message = "O ID do hóspede é obrigatório.")
        Long hospedeId,

        @NotNull(message = "O ID do quarto é obrigatório.")
        Long quartoId
        ){
}
