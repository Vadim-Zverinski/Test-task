package com.example.demo.dto.hotelResponseDto;

import com.example.demo.dto.AddressDto;
import com.example.demo.dto.ArrivalTimeDto;
import com.example.demo.dto.ContactDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelFullResponse {

    private Long id;
    private String name;
    private String description;
    private String brand;

    @Valid
    private AddressDto address;
    @Valid
    private ContactDto contacts;
    @Valid
    private ArrivalTimeDto arrivalTime;

    private List<String> amenities;
}