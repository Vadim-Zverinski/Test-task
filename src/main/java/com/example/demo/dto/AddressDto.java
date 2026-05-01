package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDto {
    private Integer houseNumber;
    private String street;
    private String city;
    private String country;
    private String postCode;
}
