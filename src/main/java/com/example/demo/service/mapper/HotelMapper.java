package com.example.demo.service.mapper;

import com.example.demo.dto.hotelRequestDto.CreateHotelRequest;
import com.example.demo.dto.hotelResponseDto.HotelFullResponse;
import com.example.demo.dto.hotelResponseDto.HotelShortResponse;
import com.example.demo.repository.entity.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HotelMapper {

    HotelFullResponse toFullDto(Hotel hotel);

    Hotel toEntity(CreateHotelRequest request);

    @Mapping(target = "address", expression = "java(formatAddress(hotel))")
    @Mapping(target = "phone", source = "contacts.phone")
    HotelShortResponse toShortDto(Hotel hotel);

    default String formatAddress(Hotel hotel) {
        var a = hotel.getAddress();
        return a.getHouseNumber() + " " +
                a.getStreet() + ", " +
                a.getCity() + ", " +
                a.getPostCode() + ", " +
                a.getCountry();
    }
}
