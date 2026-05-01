package com.example.demo.service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.dto.hotelRequestDto.CreateHotelRequest;
import com.example.demo.dto.hotelResponseDto.HotelFullResponse;
import com.example.demo.dto.hotelResponseDto.HotelShortResponse;
import com.example.demo.filter.HotelSpecification;
import com.example.demo.repository.api.HotelRepository;
import com.example.demo.repository.entity.Hotel;
import com.example.demo.service.api.IHotelService;
import com.example.demo.service.mapper.HotelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService implements IHotelService {

    private final HotelRepository hotelRepository;
    private final HotelMapper mapper;

    @Override
    public List<HotelShortResponse> getAllHotels() {
        return hotelRepository.findAll()
                .stream()
                .map(mapper::toShortDto)
                .toList();
    }

    @Override
    public HotelFullResponse getHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Hotel not found " + id));
        return mapper.toFullDto(hotel);
    }

    @Override
    public HotelFullResponse createHotel(CreateHotelRequest request) {
        Hotel hotel = mapper.toEntity(request);
        Hotel saved = hotelRepository.save(hotel);
        return mapper.toFullDto(saved);
    }

    @Override
    public List<HotelShortResponse> search(String name, String brand,
                              String city, String country, String amenity) {
        Specification<Hotel> spec = Specification
                .where(HotelSpecification.hasName(name))
                .and(HotelSpecification.hasBrand(brand))
                .and(HotelSpecification.hasCity(city))
                .and(HotelSpecification.hasCountry(country))
                .and(HotelSpecification.hasAmenity(amenity));
        return hotelRepository.findAll(spec)
                .stream()
                .map(mapper::toShortDto)
                .toList();
    }
}
