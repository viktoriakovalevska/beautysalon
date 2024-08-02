package com.example.beautysalon.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "producer")
public class Producer {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private String country;
}
