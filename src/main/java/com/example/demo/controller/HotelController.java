package com.example.demo.controller;


import com.example.demo.dto.hotelRequestDto.CreateHotelRequest;
import com.example.demo.dto.hotelResponseDto.HotelFullResponse;
import com.example.demo.dto.hotelResponseDto.HotelShortResponse;
import com.example.demo.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "Hotels API")
@RestController
@RequestMapping("/property-view")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService service;

    @GetMapping("/hotels")
    @Operation(summary = "Get all hotels")
    public List<HotelShortResponse> getAllHotels() {
        return service.getAllHotels();
    }

    @GetMapping("/hotels/{id}")
    @Operation(summary = "Get hotel by id")
    public HotelFullResponse getHotelById(@PathVariable Long id) {
        return service.getHotelById(id);
    }

    @PostMapping("/hotels")
    @Operation(summary = "Create hotels")
    public HotelShortResponse createHotel(@RequestBody CreateHotelRequest request) {
        return service.createHotel(request);
    }

    @GetMapping("/search")
    @Operation(summary = "Search")
    public List<HotelShortResponse> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String amenities
    ) {
        return service.search(name, brand, city, country, amenities);
    }

    @PostMapping("/hotels/{id}/amenities")
    @Operation(summary = "Add amenities")
    public HotelFullResponse addAmenities(
            @PathVariable Long id,
            @RequestBody List<String> amenities
    ) {
        return service.addAmenities(id, amenities);
    }

    @GetMapping("/histogram/{param}")
    @Operation(summary = "Group by")
    public Map<String, Long> histogram(@PathVariable String param) {
        return service.getHistogram(param);
    }
}
