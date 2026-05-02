package com.example.demo.service;

import com.example.demo.dto.AddressDto;
import com.example.demo.dto.ContactDto;
import com.example.demo.dto.hotelRequestDto.CreateHotelRequest;
import com.example.demo.dto.hotelResponseDto.HotelFullResponse;
import com.example.demo.dto.hotelResponseDto.HotelShortResponse;
import com.example.demo.repository.api.HotelRepository;
import com.example.demo.repository.entity.Hotel;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class HotelServiceIntegrationTest {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private HotelRepository hotelRepository;

    @Test
    void shouldCreateHotel() {

        CreateHotelRequest request = CreateHotelRequest.builder()
                .name("Hilton")
                .brand("Hilton")
                .contacts(ContactDto.builder()
                        .phone("+123456")
                        .email("test@test.com")
                        .build())
                .address(AddressDto.builder()
                        .city("minsk")
                        .build())
                .build();

        HotelShortResponse response = hotelService.createHotel(request);
        assertNotNull(response.getId());
        assertEquals("Hilton", response.getName());
        assertEquals(1, hotelRepository.count());
    }

    @Test
    void shouldGetHotelById() {

        Hotel hotel = new Hotel();
        hotel.setName("Test Hotel");

        hotel = hotelRepository.save(hotel);

        HotelFullResponse response = hotelService.getHotelById(hotel.getId());

        assertEquals("Test Hotel", response.getName());
    }

    @Test
    void shouldSearchHotels() {

        Hotel hotel = new Hotel();
        hotel.setName("Hilton");
        hotelRepository.save(hotel);

        List<HotelShortResponse> result =
                hotelService.search("Hilton", null, null, null, null);

        assertFalse(result.isEmpty());
        assertEquals("Hilton", result.get(0).getName());
    }

    @Test
    void shouldAddAmenities() {

        Hotel hotel = new Hotel();
        hotel.setName("Test");
        hotel.setAmenities(new ArrayList<>());
        hotel = hotelRepository.save(hotel);

        hotelService.addAmenities(hotel.getId(), List.of("WiFi", "Parking"));

        Hotel updated = hotelRepository.findById(hotel.getId()).get();

        assertTrue(updated.getAmenities().contains("WiFi"));
    }
}
