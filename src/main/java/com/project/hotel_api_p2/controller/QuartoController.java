package com.project.hotel_api_p2.controller;

import com.project.hotel_api_p2.Service.QuartoService;
import com.project.hotel_api_p2.dto.QuartoRequestDTO;
import com.project.hotel_api_p2.dto.QuartoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quartos")
@Tag(name = "Quartos", description = "Endpoints para gerenciamento de quartos de hotel")
public class QuartoController {

    @Autowired
    private QuartoService quartoService;

    @Operation(summary = "Cria um novo quarto", description = "Registra um novo quarto vinculado a um hotel existente.")
    @PostMapping
    public ResponseEntity<QuartoResponseDTO> createQuarto(@Valid @RequestBody QuartoRequestDTO quartoRequestDTO) {
        QuartoResponseDTO response = quartoService.createQuarto(quartoRequestDTO);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Lista todos os quartos", description = "Retorna todos os quartos cadastrados no sistema.")
    @GetMapping
    public ResponseEntity<List<QuartoResponseDTO>> getAllQuartos() {
        List<QuartoResponseDTO> quartos = quartoService.getAllQuartos();
        return ResponseEntity.ok(quartos);
    }

    @Operation(summary = "Busca quarto por ID", description = "Retorna os detalhes de um quarto específico.")
    @GetMapping("/{id}")
    public ResponseEntity<QuartoResponseDTO> getQuartoById(@PathVariable Long id) {
        QuartoResponseDTO quarto = quartoService.getQuartoById(id);
        return ResponseEntity.ok(quarto);
    }

    @Operation(summary = "Atualiza um quarto", description = "Atualiza os dados de um quarto existente, incluindo preço, tipo e disponibilidade.")
    @PutMapping("/{id}")
    public ResponseEntity<QuartoResponseDTO> updateQuarto(@PathVariable Long id,
                                                          @Valid @RequestBody QuartoRequestDTO quartoRequestDTO) {
        QuartoResponseDTO updated = quartoService.updateQuarto(id, quartoRequestDTO);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Exclui um quarto", description = "Remove um quarto do sistema com base no ID informado.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuarto(@PathVariable Long id) {
        quartoService.deleteQuarto(id);
        return ResponseEntity.noContent().build();
    }
}
