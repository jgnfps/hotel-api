package com.project.hotel_api_p2.repository;

import com.project.hotel_api_p2.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
}
