package com.project.hotel_api_p2.repository;

import com.project.hotel_api_p2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
