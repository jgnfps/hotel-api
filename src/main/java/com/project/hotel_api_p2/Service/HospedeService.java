package com.project.hotel_api_p2.Service;

import com.project.hotel_api_p2.dto.HospedeRequestDTO;
import com.project.hotel_api_p2.dto.HospedeResponseDTO;
import com.project.hotel_api_p2.entity.Hospede;
import com.project.hotel_api_p2.repository.HospedeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospedeService {

    private final HospedeRepository hospedeRepository;

    public HospedeService(HospedeRepository hospedeRepository) {
        this.hospedeRepository = hospedeRepository;
    }

    public HospedeResponseDTO createHospede(HospedeRequestDTO dto) {
        Hospede hospede = new Hospede();
        hospede.setNomeCompleto(dto.nomeCompleto());
        hospede.setCpf(dto.cpf());
        hospede.setEmail(dto.email());
        hospede.setTelefone(dto.telefone());

        hospedeRepository.save(hospede);

        return new HospedeResponseDTO(
                hospede.getId(),
                hospede.getNomeCompleto(),
                hospede.getCpf(),
                hospede.getEmail(),
                hospede.getTelefone()
        );
    }

    public List<HospedeResponseDTO> getAllHospedes() {
        return hospedeRepository.findAll().stream()
                .map(h -> new HospedeResponseDTO(
                        h.getId(),
                        h.getNomeCompleto(),
                        h.getCpf(),
                        h.getEmail(),
                        h.getTelefone()
                ))
                .toList();
    }

    public HospedeResponseDTO getHospedeById(Long id) {
        Hospede hospede = hospedeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hóspede não encontrado com o ID: " + id));

        return new HospedeResponseDTO(
                hospede.getId(),
                hospede.getNomeCompleto(),
                hospede.getCpf(),
                hospede.getEmail(),
                hospede.getTelefone()
        );
    }

    public HospedeResponseDTO updateHospede(Long id, HospedeRequestDTO dto) {
        Hospede hospede = hospedeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hóspede não encontrado com o ID: " + id));

        hospede.setNomeCompleto(dto.nomeCompleto());
        hospede.setCpf(dto.cpf());
        hospede.setEmail(dto.email());
        hospede.setTelefone(dto.telefone());

        hospedeRepository.save(hospede);

        return new HospedeResponseDTO(
                hospede.getId(),
                hospede.getNomeCompleto(),
                hospede.getCpf(),
                hospede.getEmail(),
                hospede.getTelefone()
        );
    }

    public void deleteHospede(Long id) {
        Hospede hospede = hospedeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hóspede não encontrado com o ID: " + id));
        hospedeRepository.delete(hospede);
    }
}
