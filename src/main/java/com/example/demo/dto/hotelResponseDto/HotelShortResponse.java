package com.example.demo.dto.hotelResponseDto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelShortResponse {

    private Long id;
    private String name;
    private String description;
    private String address;
    private String phone;
}