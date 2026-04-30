package com.example.demo.service;

import com.example.demo.filter.HotelSpecification;
import com.example.demo.repository.api.HotelRepository;
import com.example.demo.repository.entity.Hotel;
import com.example.demo.service.api.IHotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService implements IHotelService {

    private final HotelRepository hotelRepository;

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id).
                orElseThrow(()->new RuntimeException("Hotel not found " + id));
        //TODO пока так потом изменить
    }

    @Override
    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> search(String name, String brand,
                              String city, String country, String amenity) {
        Specification<Hotel> spec = Specification
                .where(HotelSpecification.hasName(name))
                .and(HotelSpecification.hasBrand(brand))
                .and(HotelSpecification.hasCity(city))
                .and(HotelSpecification.hasCountry(country))
                .and(HotelSpecification.hasAmenity(amenity));

        return hotelRepository.findAll(spec);
    }
}
