package com.project.hotel_api_p2.repository;

import com.project.hotel_api_p2.entity.Hospede;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospedeRepository extends JpaRepository<Hospede, Long> {
}
