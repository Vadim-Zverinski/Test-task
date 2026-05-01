package com.example.demo.dto;

import com.example.demo.repository.entity.Address;
import com.example.demo.repository.entity.ArrivalTime;
import com.example.demo.repository.entity.Contacts;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateHotelRequest {

    private String name;
    private String description;
    private String brand;

    private Address address;
    private Contacts contacts;
    private ArrivalTime arrivalTime;
}