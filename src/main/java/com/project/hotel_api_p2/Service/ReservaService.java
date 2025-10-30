package com.project.hotel_api_p2.Service;


import com.project.hotel_api_p2.dto.ReservaRequestDTO;
import com.project.hotel_api_p2.dto.ReservaResponseDTO;
import com.project.hotel_api_p2.entity.Hospede;
import com.project.hotel_api_p2.entity.Quarto;
import com.project.hotel_api_p2.entity.Reserva;
import com.project.hotel_api_p2.entity.enums.StatusReserva;
import com.project.hotel_api_p2.repository.HospedeRepository;
import com.project.hotel_api_p2.repository.QuartoRepository;
import com.project.hotel_api_p2.repository.ReservaRespository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {
    @Autowired
    private ReservaRespository reservaRespository;
    @Autowired
    private QuartoRepository quartoRepository;
    @Autowired
    private HospedeRepository hospedeRepository;

    public ReservaResponseDTO createReserva(ReservaRequestDTO reservaRequestDTO) {

        Hospede hospede = hospedeRepository.findById(reservaRequestDTO.hospedeId())
                .orElseThrow(()-> new EntityNotFoundException("Hospede não encontrado com o Id: " + reservaRequestDTO.hospedeId()));

        Quarto quarto = quartoRepository.findById(reservaRequestDTO.quartoId())
                .orElseThrow(()-> new EntityNotFoundException("Quarto não encontrado com o Id: " + reservaRequestDTO.quartoId()));

        StatusReserva status = StatusReserva.valueOf(reservaRequestDTO.statusReserva().toUpperCase());

        Reserva reserva = new Reserva();

        reserva.setDataCheckIn(reservaRequestDTO.dataCheckIn());
        reserva.setDataCheckOut(reservaRequestDTO.dataCheckOut());
        reserva.setValorTotal(reservaRequestDTO.valorTotal());
        reserva.setStatusReserva(status);
        reserva.setHospede(hospede);
        reserva.setQuarto(quarto);

        reservaRespository.save(reserva);

        return new ReservaResponseDTO(
                reserva.getId(),
                reserva.getDataCheckIn(),
                reserva.getDataCheckOut(),
                reserva.getValorTotal(),
                reserva.getStatusReserva().name(),
                reserva.getHospede().getNomeCompleto(),
                reserva.getQuarto().getNumero()
        );
    }

    public void deleteReserva(Long reservaId) {
        Reserva reserva = reservaRespository.findById(reservaId)
                .orElseThrow(()-> new EntityNotFoundException("Reserva não encontrada com o Id: " + reservaId));
        reservaRespository.delete(reserva);
    }

    public List<ReservaResponseDTO> getAllReservas(){
        return reservaRespository.findAll()
                .stream()
                .map(reserva -> new ReservaResponseDTO(
                        reserva.getId(),
                        reserva.getDataCheckIn(),
                        reserva.getDataCheckOut(),
                        reserva.getValorTotal(),
                        reserva.getStatusReserva().name(),
                        reserva.getHospede().getNomeCompleto(),
                        reserva.getQuarto().getNumero()
                ))
                .toList();
    }

    public ReservaResponseDTO getReservaById(Long reservaId) {
        Reserva reserva = reservaRespository.findById(reservaId)
                .orElseThrow(()-> new EntityNotFoundException("Reserva não encontrada com o Id: " + reservaId));

        return new ReservaResponseDTO(
                reserva.getId(),
                reserva.getDataCheckIn(),
                reserva.getDataCheckOut(),
                reserva.getValorTotal(),
                reserva.getStatusReserva().name(),
                reserva.getHospede().getNomeCompleto(),
                reserva.getQuarto().getNumero()
        );
    }

    public ReservaResponseDTO updateReserva(Long reservaId, ReservaRequestDTO reservaRequestDTO) {
        Reserva reserva = reservaRespository.findById(reservaId)
                .orElseThrow(()-> new EntityNotFoundException("Reserva não encontrada com o Id: " + reservaId));

        Hospede hospede = hospedeRepository.findById(reservaRequestDTO.hospedeId())
                .orElseThrow(()-> new EntityNotFoundException("Hospede não encontrado com o Id: " + reservaRequestDTO.hospedeId()));

        Quarto quarto = quartoRepository.findById(reservaRequestDTO.quartoId())
                .orElseThrow(()-> new EntityNotFoundException("Quarto não encontrado com o Id: " + reservaRequestDTO.quartoId()));

        StatusReserva status = StatusReserva.valueOf(reservaRequestDTO.statusReserva().toUpperCase());

        reserva.setDataCheckIn(reservaRequestDTO.dataCheckIn());
        reserva.setDataCheckOut(reservaRequestDTO.dataCheckOut());
        reserva.setValorTotal(reservaRequestDTO.valorTotal());
        reserva.setStatusReserva(status);
        reserva.setHospede(hospede);
        reserva.setQuarto(quarto);

        reservaRespository.save(reserva);

        return new ReservaResponseDTO(
                reserva.getId(),
                reserva.getDataCheckIn(),
                reserva.getDataCheckOut(),
                reserva.getValorTotal(),
                reserva.getStatusReserva().name(),
                reserva.getHospede().getNomeCompleto(),
                reserva.getQuarto().getNumero()
        );
    }

}