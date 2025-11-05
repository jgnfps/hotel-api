package com.project.hotel_api_p2.controller;

import com.project.hotel_api_p2.Service.HotelService;
import com.project.hotel_api_p2.dto.HotelRequestDTO;
import com.project.hotel_api_p2.dto.HotelResponseDTO;
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
@RequestMapping("/hoteis")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @Operation(summary = "Cria um novo hotel")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Hotel criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<HotelResponseDTO> create(@Valid @RequestBody HotelRequestDTO dto) {
        HotelResponseDTO response = hotelService.createHotel(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Lista todos os hotéis")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<HotelResponseDTO>> getAll() {
        return ResponseEntity.ok(hotelService.getAllHoteis());
    }

    @Operation(summary = "Busca hotel por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Hotel encontrado"),
            @ApiResponse(responseCode = "404", description = "Hotel não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<HotelResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }

    @Operation(summary = "Atualiza um hotel existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Hotel atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Hotel não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<HotelResponseDTO> update(@PathVariable Long id, @Valid @RequestBody HotelRequestDTO dto) {
        return ResponseEntity.ok(hotelService.updateHotel(id, dto));
    }

    @Operation(summary = "Exclui um hotel pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Hotel excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Hotel não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }
}
