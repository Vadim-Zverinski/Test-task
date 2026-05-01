package com.example.demo.service.api.Util;

import com.example.demo.dto.hotelRequestDto.CreateHotelRequest;

public interface IHotelPolicy {

    void checkCreateAllowed(CreateHotelRequest request);
}
