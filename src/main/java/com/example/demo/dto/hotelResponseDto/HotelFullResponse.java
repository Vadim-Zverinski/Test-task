package com.example.demo.dto.hotelResponseDto;

import com.example.demo.repository.entity.Address;
import com.example.demo.repository.entity.ArrivalTime;
import com.example.demo.repository.entity.Contacts;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class HotelFullResponse {

    private Long id;
    private String name;
    private String description;
    private String brand;

    private Address address;
    private Contacts contacts;
    private ArrivalTime arrivalTime;

    private List<String> amenities;
}