package com.example.demo.dto.hotelResponseDto;

import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HotelShortResponse {

    private Long id;
    private String name;
    private String description;
    private String address;
    private String phone;
}