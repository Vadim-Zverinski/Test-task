package com.example.demo.service;

import com.example.demo.dto.hotelRequestDto.CreateHotelRequest;
import com.example.demo.dto.hotelResponseDto.HotelFullResponse;
import com.example.demo.dto.hotelResponseDto.HotelShortResponse;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.api.HotelRepository;
import com.example.demo.repository.entity.Hotel;
import com.example.demo.service.Util.HotelNormalizer;
import com.example.demo.service.Util.HotelPolicy;
import com.example.demo.service.Util.HotelValidator;
import com.example.demo.service.mapper.HotelMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HotelServiceTest {

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private HotelMapper mapper;

    @Mock
    private HotelValidator validator;

    @Mock
    private HotelPolicy policy;

    @Mock
    private HotelNormalizer normalizer;

    @InjectMocks
    private HotelService service;

    @Test
    void getAllHotels_shouldReturnList() {

        Hotel hotel = new Hotel();
        when(hotelRepository.findAll()).thenReturn(List.of(hotel));

        when(mapper.toShortDto(any())).thenReturn(null);

        var result = service.getAllHotels();

        assertThat(result).isNotNull();
        verify(hotelRepository).findAll();
    }

    @Test
    void getAllHotels_shouldMapToDto() {
        Hotel hotel = new Hotel();
        var dto = HotelShortResponse.builder()
                .id(1L)
                .name("Test")
                .description("Desc")
                .address("addr")
                .phone("123")
                .build();

        when(hotelRepository.findAll()).thenReturn(List.of(hotel));
        when(mapper.toShortDto(hotel)).thenReturn(dto);

        var result = service.getAllHotels();

        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(dto);
    }

    @Test
    void getHotelById_shouldThrowIfNotFound() {
        when(hotelRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getHotelById(1L))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void createHotel_shouldSaveHotel() {
        var request = CreateHotelRequest.builder()
                .name("Hilton")
                .build();
        var hotel = new Hotel();
        var saved = new Hotel();

        when(mapper.toEntity(request)).thenReturn(hotel);
        when(hotelRepository.save(hotel)).thenReturn(saved);
        when(mapper.toFullDto(any()))
                .thenReturn(HotelFullResponse.builder().build());

        var result = service.createHotel(request);

        assertThat(result).isNotNull();
        verify(hotelRepository).save(hotel);
    }
}