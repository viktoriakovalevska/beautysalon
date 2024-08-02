package com.example.beautysalon.dto;

import com.example.beautysalon.entities.Producer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProducerResponseDTO {

    private String id;
    private String name;
    private String country;

    public ProducerResponseDTO(Producer producer) {
        this.id = producer.getId();
        this.name = producer.getName();
        this.country = producer.getCountry();
    }
}
