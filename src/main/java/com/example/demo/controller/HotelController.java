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

    @Operation(summary = "Get all hotels")
    @GetMapping("/hotels")
    public List<HotelShortResponse> getAllHotels() {
        return service.getAllHotels();
    }

    @Operation(summary = "Get hotels by id")
    @GetMapping("/hotels/{id}")
    public HotelFullResponse getHotelById(@PathVariable Long id) {
        return service.getHotelById(id);
    }

    @Operation(summary = "Set hotels")
    @PostMapping("/hotels")
    public HotelFullResponse createHotel(@Valid @RequestBody CreateHotelRequest request) {
        return service.createHotel(request);
    }

    @Operation(summary = "Search")
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

    @Operation(summary = "Set amenities")
    @PostMapping("/hotels/{id}/amenities")
    public HotelFullResponse addAmenities(
            @PathVariable Long id,
            @RequestBody List<String> amenities
    ) {
        return service.addAmenities(id, amenities);
    }

    @Operation(summary = "Group by")
    @GetMapping("/histogram/{param}")
    public Map<String, Long> histogram(@PathVariable String param) {
        return service.getHistogram(param);
    }
}
