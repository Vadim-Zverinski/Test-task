package com.example.demo.service.Util;

import com.example.demo.exception.BadRequestException;
import com.example.demo.dto.hotelRequestDto.CreateHotelRequest;
import com.example.demo.service.api.Util.IHotelValidator;
import org.springframework.stereotype.Component;

@Component
public class HotelValidator implements IHotelValidator {

    public void validate(CreateHotelRequest request)  {

        if (request.getName() == null || request.getName().length() < 3) {
            throw new BadRequestException("Name too short");
        }

        if (request.getContacts() == null || request.getContacts().getPhone() == null) {
            throw new BadRequestException("Phone is required");
        }
    }
}