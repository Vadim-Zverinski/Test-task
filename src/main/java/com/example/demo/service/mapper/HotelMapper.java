package com.example.demo.service.mapper;

import com.example.demo.dto.hotelRequestDto.CreateHotelRequest;
import com.example.demo.dto.hotelResponseDto.HotelFullResponse;
import com.example.demo.dto.hotelResponseDto.HotelShortResponse;
import com.example.demo.repository.entity.Address;
import com.example.demo.repository.entity.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface HotelMapper {

    HotelFullResponse toFullDto(Hotel hotel);
    Hotel toEntity(CreateHotelRequest request);

    @Mapping(target = "address", source = "address", qualifiedByName = "formatAddress")
    @Mapping(target = "phone", source = "contacts.phone")
    HotelShortResponse toShortDto(Hotel hotel);

    @Named("formatAddress")
    default String formatAddress(Address address) {
        if (address == null) return null;

        return address.getHouseNumber() + " " +
                address.getStreet() + ", " +
                address.getCity() + ", " +
                address.getPostCode() + ", " +
                address.getCountry();
    }
}
