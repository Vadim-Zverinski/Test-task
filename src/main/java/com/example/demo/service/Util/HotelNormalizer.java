package com.example.demo.service.Util;

import com.example.demo.dto.hotelRequestDto.CreateHotelRequest;
import com.example.demo.service.api.Util.IHotelNormalizer;
import org.springframework.stereotype.Component;

@Component
public class HotelNormalizer implements IHotelNormalizer {

    public void normalize(CreateHotelRequest request) {

        request.setName(request.getName().trim());

        if (request.getBrand() != null) {
            request.setBrand(request.getBrand().trim());
        }

        if (request.getAddress() != null && request.getAddress().getCity() != null) {
            request.getAddress().setCity(
                    request.getAddress().getCity().trim().toLowerCase()
            );
        }
    }
}