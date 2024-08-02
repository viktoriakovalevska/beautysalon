package com.example.beautysalon.dto;

import lombok.Data;

@Data
public class GoodsRequestDTO {
    private String name;
    private String producerId;
    private String description;
    private String measurement;
    private int baseMeasurement;
}
