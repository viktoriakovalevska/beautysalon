package com.example.beautysalon.dto;

import com.example.beautysalon.entities.Goods;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsResponseDTO {
    private long id;
    private String name;
    private ProducerResponseDTO producer;
    private String description;
    private String measurement;
    private int baseMeasurement;

    public GoodsResponseDTO(Goods goods) {
        this.id = goods.getId();
        this.name = goods.getName();
        this.producer = new ProducerResponseDTO(goods.getProducer());
        this.description = goods.getDescription();
        this.measurement = goods.getMeasurement();
        this.baseMeasurement = goods.getBaseMeasurement();
    }
}
