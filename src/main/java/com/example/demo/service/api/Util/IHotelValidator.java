package com.example.demo.service.api.Util;

import com.example.demo.dto.hotelRequestDto.CreateHotelRequest;

public interface IHotelValidator {

    void validate(CreateHotelRequest request);
}
