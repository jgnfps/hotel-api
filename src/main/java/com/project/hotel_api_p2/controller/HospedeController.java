package com.project.hotel_api_p2.controller;

import com.project.hotel_api_p2.Service.HospedeService;
import com.project.hotel_api_p2.dto.HospedeRequestDTO;
import com.project.hotel_api_p2.dto.HospedeResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospedes")
public class HospedeController {

    private final HospedeService hospedeService;

    public HospedeController(HospedeService hospedeService) {
        this.hospedeService = hospedeService;
    }

    @Operation(summary = "Cadastra um novo hóspede")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Hóspede cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<HospedeResponseDTO> create(@Valid @RequestBody HospedeRequestDTO dto) {
        HospedeResponseDTO response = hospedeService.createHospede(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Lista todos os hóspedes")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<HospedeResponseDTO>> getAll() {
        return ResponseEntity.ok(hospedeService.getAllHospedes());
    }

    @Operation(summary = "Busca um hóspede por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Hóspede encontrado"),
            @ApiResponse(responseCode = "404", description = "Hóspede não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<HospedeResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(hospedeService.getHospedeById(id));
    }

    @Operation(summary = "Atualiza um hóspede existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Hóspede atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Hóspede não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<HospedeResponseDTO> update(@PathVariable Long id, @Valid @RequestBody HospedeRequestDTO dto) {
        return ResponseEntity.ok(hospedeService.updateHospede(id, dto));
    }

    @Operation(summary = "Exclui um hóspede pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Hóspede excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Hóspede não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        hospedeService.deleteHospede(id);
        return ResponseEntity.noContent().build();
    }
}
