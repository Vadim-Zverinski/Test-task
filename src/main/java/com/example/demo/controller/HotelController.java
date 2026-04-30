package com.example.demo.controller;


import com.example.demo.repository.entity.Hotel;
import com.example.demo.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/property-view")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService service;

    @GetMapping("/hotels")
    public List<Hotel> getAllHotels() {
        return service.getAllHotels();
    }

    @GetMapping("/hotels/{id}")
    public Hotel getHotelById(@PathVariable Long id) {
        return service.getHotelById(id);
    }

    @PostMapping("/hotels")
    public Hotel createHotel(@RequestBody Hotel hotel) {
        return service.createHotel(hotel);
    }

    @GetMapping("/search")
    public List<Hotel> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String amenities
    ) {
        return service.search(name, brand, city, country, amenities);
    }
}
