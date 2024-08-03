package com.example.beautysalon.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Duration;

@Data
@Builder
@Document(collection = "offer")
public class Offer {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private Duration period;

}
