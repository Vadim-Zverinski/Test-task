package com.example.demo.service.api;

import com.example.demo.dto.hotelRequestDto.CreateHotelRequest;
import com.example.demo.dto.hotelResponseDto.HotelFullResponse;
import com.example.demo.dto.hotelResponseDto.HotelShortResponse;

import java.util.List;

public interface IHotelService {

    List<HotelShortResponse> getAllHotels();
    HotelFullResponse getHotelById(Long id);
    HotelFullResponse createHotel(CreateHotelRequest request);
    List<HotelShortResponse> search(String name, String brand, String city, String country, String amenity);
}
