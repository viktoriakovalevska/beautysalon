package com.example.beautysalon.dto;

import com.example.beautysalon.entities.Offer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferResponseDTO {
    private String id;
    private String name;
    private Integer period;
    public OfferResponseDTO(Offer offer){
        this.id = offer.getId();
        this.name =offer.getName();
        this.period = Math.toIntExact(offer.getPeriod().getSeconds())/60;
    }
}


