package com.example.beautysalon.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "goods")
public class Goods {
    @Id
    private long id;
    private String name;
    @DBRef
    private Producer producer;
    private String description;
    private String measurement;
    private int baseMeasurement;
}
