package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArrivalTimeDto {
    private String checkIn;
    private String checkOut;
}