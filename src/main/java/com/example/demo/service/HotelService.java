package com.example.demo.service;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.dto.hotelRequestDto.CreateHotelRequest;
import com.example.demo.dto.hotelResponseDto.HotelFullResponse;
import com.example.demo.dto.hotelResponseDto.HotelShortResponse;
import com.example.demo.filter.HotelSpecification;
import com.example.demo.repository.api.HotelRepository;
import com.example.demo.repository.entity.Hotel;
import com.example.demo.service.Util.HotelNormalizer;
import com.example.demo.service.Util.HotelPolicy;
import com.example.demo.service.Util.HotelValidator;
import com.example.demo.service.api.IHotelService;
import com.example.demo.service.mapper.HotelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class HotelService implements IHotelService {

    private final HotelRepository hotelRepository;
    private final HotelMapper mapper;
    private final HotelValidator validator;
    private final HotelPolicy policy;
    private final HotelNormalizer normalizer;

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

    public HotelShortResponse createHotel(CreateHotelRequest request) {

        validator.validate(request);
        policy.checkCreateAllowed(request);
        normalizer.normalize(request);
        Hotel hotel = mapper.toEntity(request);
        Hotel saved = hotelRepository.save(hotel);
        return mapper.toShortDto(saved);
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

    @Override
    public HotelFullResponse addAmenities(Long id, List<String> amenities) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Hotel not found " + id));

        if (hotel.getAmenities() == null) {
            hotel.setAmenities(new ArrayList<>());
        }

        Set<String> uniqueAmenities = new HashSet<>(hotel.getAmenities());
        uniqueAmenities.addAll(amenities);
        hotel.setAmenities(new ArrayList<>(uniqueAmenities));
        Hotel saved = hotelRepository.save(hotel);
        return mapper.toFullDto(saved);
    }

    @Override
    public Map<String, Long> getHistogram(String param) {

        List<Hotel> hotels = hotelRepository.findAll();
        return switch (param.toLowerCase()) {

            case "brand" -> hotels.stream()
                    .collect(Collectors.groupingBy(
                            Hotel::getBrand,
                            Collectors.counting()
                    ));

            case "city" -> hotels.stream()
                    .collect(Collectors.groupingBy(
                            h -> h.getAddress() != null ? h.getAddress().getCity(): "unknown",
                            Collectors.counting()
                    ));

            case "country" -> hotels.stream()
                    .collect(Collectors.groupingBy(
                            h -> h.getAddress() != null ? h.getAddress().getCountry(): "unknown",
                            Collectors.counting()
                    ));

            case "amenities" -> hotels.stream()
                    .flatMap(h -> h.getAmenities() == null
                            ? Stream.empty()
                            : h.getAmenities().stream())
                    .collect(Collectors.groupingBy(
                            a -> a,
                            Collectors.counting()
                    ));

            default -> throw new BadRequestException("Unknown histogram param: " + param);
        };
    }
}
