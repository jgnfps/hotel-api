package com.project.hotel_api_p2.controller;

import com.project.hotel_api_p2.Service.ReservaService;
import com.project.hotel_api_p2.dto.ReservaRequestDTO;
import com.project.hotel_api_p2.dto.ReservaResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
@Tag(name = "Reservas", description = "Endpoints para gerenciamento de reservas de hotel")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @Operation(summary = "Cria uma nova reserva", description = "Registra uma nova reserva associando um hóspede e um quarto.")
    @PostMapping
    public ResponseEntity<ReservaResponseDTO> createReserva(@Valid @RequestBody ReservaRequestDTO reservaRequestDTO) {
        ReservaResponseDTO response = reservaService.createReserva(reservaRequestDTO);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Lista todas as reservas", description = "Retorna uma lista com todas as reservas cadastradas no sistema.")
    @GetMapping
    public ResponseEntity<List<ReservaResponseDTO>> getAllReservas() {
        List<ReservaResponseDTO> reservas = reservaService.getAllReservas();
        return ResponseEntity.ok(reservas);
    }

    @Operation(summary = "Busca reserva por ID", description = "Retorna os dados detalhados de uma reserva específica.")
    @GetMapping("/{id}")
    public ResponseEntity<ReservaResponseDTO> getReservaById(@PathVariable Long id) {
        ReservaResponseDTO reserva = reservaService.getReservaById(id);
        return ResponseEntity.ok(reserva);
    }

    @Operation(summary = "Atualiza uma reserva", description = "Atualiza as informações de uma reserva existente, incluindo hóspede, quarto e status.")
    @PutMapping("/{id}")
    public ResponseEntity<ReservaResponseDTO> updateReserva(@PathVariable Long id,
                                                            @Valid @RequestBody ReservaRequestDTO reservaRequestDTO) {
        ReservaResponseDTO updated = reservaService.updateReserva(id, reservaRequestDTO);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Exclui uma reserva", description = "Remove uma reserva do sistema com base no ID informado.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        reservaService.deleteReserva(id);
        return ResponseEntity.noContent().build();
    }
}
