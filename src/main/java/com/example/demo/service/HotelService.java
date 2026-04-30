package com.example.demo.service;

import com.example.demo.repository.entity.Hotel;
import com.example.demo.service.api.IHotelService;

import java.util.List;

public class HotelService implements IHotelService {

    @Override
    public List<Hotel> getAllHotels() {
        return List.of();
    }

    @Override
    public Hotel getHotelById(Long id) {
        return null;
    }

    @Override
    public Hotel createHotel(Hotel hotel) {
        return null;
    }
}
