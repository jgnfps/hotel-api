package com.project.hotel_api_p2.repository;

import com.project.hotel_api_p2.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRespository extends JpaRepository<Reserva, Integer> {
}
