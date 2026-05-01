package com.example.demo.controller;


import com.example.demo.dto.hotelRequestDto.CreateHotelRequest;
import com.example.demo.dto.hotelResponseDto.HotelFullResponse;
import com.example.demo.dto.hotelResponseDto.HotelShortResponse;
import com.example.demo.service.HotelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/property-view")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService service;

    @GetMapping("/hotels")
    public List<HotelShortResponse> getAllHotels() {
        return service.getAllHotels();
    }

    @GetMapping("/hotels/{id}")
    public HotelFullResponse getHotelById(@PathVariable Long id) {
        return service.getHotelById(id);
    }

    @PostMapping("/hotels")
    public HotelFullResponse createHotel(@Valid @RequestBody CreateHotelRequest request) {
        return service.createHotel(request);
    }

    @GetMapping("/search")
    public List<HotelShortResponse> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String amenities
    ) {
        return service.search(name, brand, city, country, amenities);
    }
}
