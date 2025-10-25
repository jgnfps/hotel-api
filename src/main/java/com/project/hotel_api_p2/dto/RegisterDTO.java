package com.project.hotel_api_p2.dto;

import com.project.hotel_api_p2.entity.enums.UserRole;

public record RegisterDTO (String username, String password, UserRole role, String email){
}
