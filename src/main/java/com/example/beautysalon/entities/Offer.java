package com.example.beautysalon.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Duration;

@Data
@Builder
@Document(collection = "offer")
public class Offer {
    private String id;
    private String name;
    private Duration period;

}
