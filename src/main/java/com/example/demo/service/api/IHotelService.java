package com.example.demo.service.api;

import com.example.demo.repository.entity.Hotel;

import java.util.List;

public interface IHotelService {

    List<Hotel> getAllHotels();
    Hotel getHotelById(Long id);
    Hotel createHotel(Hotel hotel);
}
