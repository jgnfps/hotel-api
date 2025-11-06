package com.project.hotel_api_p2.Service;

import com.project.hotel_api_p2.dto.QuartoRequestDTO;
import com.project.hotel_api_p2.dto.QuartoResponseDTO;
import com.project.hotel_api_p2.entity.Hotel;
import com.project.hotel_api_p2.entity.Quarto;
import com.project.hotel_api_p2.repository.HotelRepository;
import com.project.hotel_api_p2.repository.QuartoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuartoService {

    @Autowired
    private QuartoRepository quartoRepository;

    @Autowired
    private HotelRepository hotelRepository;


    public QuartoResponseDTO createQuarto(QuartoRequestDTO quartoRequestDTO) {

        Hotel hotel = hotelRepository.findById(quartoRequestDTO.hotelId())
                .orElseThrow(() -> new EntityNotFoundException("Hotel não encontrado com o ID: " + quartoRequestDTO.hotelId()));

        Quarto quarto = new Quarto();
        quarto.setNumero(quartoRequestDTO.numero());
        quarto.setTipo(quartoRequestDTO.tipo());
        quarto.setPrecoDiaria(quartoRequestDTO.precoDiaria());
        quarto.setDisponivel(quartoRequestDTO.disponivel());
        quarto.setHotel(hotel);
        quarto.setCapacidadeHospedes(quartoRequestDTO.capacidadeHospedes());

        quartoRepository.save(quarto);

        return new QuartoResponseDTO(
                quarto.getId(),
                quarto.getNumero(),
                quarto.getTipo(),
                quarto.getPrecoDiaria(),
                quarto.getDisponivel(),
                quarto.getHotel() != null ? quarto.getHotel().getNome() : null,
                quarto.getCapacidadeHospedes()
        );
    }


    public void deleteQuarto(Long quartoId) {
        Quarto quarto = quartoRepository.findById(quartoId)
                .orElseThrow(() -> new EntityNotFoundException("Quarto não encontrado com o Id: " + quartoId));
        quartoRepository.delete(quarto);
    }


    public List<QuartoResponseDTO> getAllQuartos() {
        return quartoRepository.findAll()
                .stream()
                .map(quarto -> new QuartoResponseDTO(
                        quarto.getId(),
                        quarto.getNumero(),
                        quarto.getTipo(),
                        quarto.getPrecoDiaria(),
                        quarto.getDisponivel(),
                        quarto.getHotel() != null ? quarto.getHotel().getNome() : null,
                        quarto.getCapacidadeHospedes()
                ))
                .toList();
    }


    public QuartoResponseDTO getQuartoById(Long quartoId) {
        Quarto quarto = quartoRepository.findById(quartoId)
                .orElseThrow(() -> new EntityNotFoundException("Quarto não encontrado com o Id: " + quartoId));

        return new QuartoResponseDTO(
                quarto.getId(),
                quarto.getNumero(),
                quarto.getTipo(),
                quarto.getPrecoDiaria(),
                quarto.getDisponivel(),
                quarto.getHotel() != null ? quarto.getHotel().getNome() : null,
                quarto.getCapacidadeHospedes()
        );
    }

    public QuartoResponseDTO updateQuarto(Long quartoId, QuartoRequestDTO quartoRequestDTO) {
        Quarto quarto = quartoRepository.findById(quartoId)
                .orElseThrow(() -> new EntityNotFoundException("Quarto não encontrado com o Id: " + quartoId));

        Hotel hotel = hotelRepository.findById(quartoRequestDTO.hotelId())
                .orElseThrow(() -> new EntityNotFoundException("Hotel não encontrado com o ID: " + quartoRequestDTO.hotelId()));

        quarto.setNumero(quartoRequestDTO.numero());
        quarto.setTipo(quartoRequestDTO.tipo());
        quarto.setPrecoDiaria(quartoRequestDTO.precoDiaria());
        quarto.setDisponivel(quartoRequestDTO.disponivel());
        quarto.setHotel(hotel);
        quarto.setCapacidadeHospedes(quartoRequestDTO.capacidadeHospedes());

        quartoRepository.save(quarto);

        return new QuartoResponseDTO(
                quarto.getId(),
                quarto.getNumero(),
                quarto.getTipo(),
                quarto.getPrecoDiaria(),
                quarto.getDisponivel(),
                quarto.getHotel() != null ? quarto.getHotel().getNome() : null,
                quarto.getCapacidadeHospedes()
        );
    }
}
