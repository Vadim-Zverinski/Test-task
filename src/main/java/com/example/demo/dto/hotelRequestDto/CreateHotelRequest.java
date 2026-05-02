package com.example.demo.dto.hotelRequestDto;

import com.example.demo.dto.AddressDto;
import com.example.demo.dto.ArrivalTimeDto;
import com.example.demo.dto.ContactDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateHotelRequest {

    @NotBlank
    private String name;

    @Size(max = 2000)
    private String description;
    private String brand;

    @Valid
    private AddressDto address;
    @Valid
    private ContactDto contacts;
    @Valid
    private ArrivalTimeDto arrivalTime;
}