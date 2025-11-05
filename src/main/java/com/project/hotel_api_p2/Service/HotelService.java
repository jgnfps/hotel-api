package com.project.hotel_api_p2.Service;

import com.project.hotel_api_p2.dto.HotelRequestDTO;
import com.project.hotel_api_p2.dto.HotelResponseDTO;
import com.project.hotel_api_p2.entity.Hotel;
import com.project.hotel_api_p2.repository.HotelRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public HotelResponseDTO createHotel(HotelRequestDTO dto) {
        Hotel hotel = new Hotel();
        hotel.setNome(dto.nome());
        hotel.setEndereco(dto.endereco());
        hotel.setTelefone(dto.telefone());
        hotel.setEmail(dto.email());
        hotelRepository.save(hotel);

        return new HotelResponseDTO(
                hotel.getId(),
                hotel.getNome(),
                hotel.getEndereco(),
                hotel.getTelefone(),
                hotel.getEmail()
        );
    }

    public List<HotelResponseDTO> getAllHoteis() {
        return hotelRepository.findAll()
                .stream()
                .map(hotel -> new HotelResponseDTO(
                        hotel.getId(),
                        hotel.getNome(),
                        hotel.getEndereco(),
                        hotel.getTelefone(),
                        hotel.getEmail()
                ))
                .toList();
    }

    public HotelResponseDTO getHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hotel não encontrado com o ID: " + id));

        return new HotelResponseDTO(
                hotel.getId(),
                hotel.getNome(),
                hotel.getEndereco(),
                hotel.getTelefone(),
                hotel.getEmail()
        );
    }

    public HotelResponseDTO updateHotel(Long id, HotelRequestDTO dto) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hotel não encontrado com o ID: " + id));

        hotel.setNome(dto.nome());
        hotel.setEndereco(dto.endereco());
        hotel.setTelefone(dto.telefone());
        hotel.setEmail(dto.email());

        hotelRepository.save(hotel);

        return new HotelResponseDTO(
                hotel.getId(),
                hotel.getNome(),
                hotel.getEndereco(),
                hotel.getTelefone(),
                hotel.getEmail()
        );
    }

    public void deleteHotel(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hotel não encontrado com o ID: " + id));

        hotelRepository.delete(hotel);
    }
}
