package com.example.demo.service.Util;

import com.example.demo.dto.hotelRequestDto.CreateHotelRequest;
import com.example.demo.exception.ConflictException;
import com.example.demo.repository.api.HotelRepository;
import com.example.demo.service.api.Util.IHotelPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HotelPolicy implements IHotelPolicy {

    private final HotelRepository repository;

    public void checkCreateAllowed(CreateHotelRequest request) {

        boolean exists = repository.existsByNameAndAddress_City(
                request.getName(),
                request.getAddress().getCity()
        );

        if (exists) {
            throw new ConflictException("Hotel already exists in this city");
        }
    }
}