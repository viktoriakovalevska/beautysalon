package com.example.beautysalon.entities;

import com.example.beautysalon.common.OrderStatus;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@Document(collection = "order")
public class Order {
    @Id
    private String id;
    @DBRef
    private User client;
    @DBRef
    private User master;
    @DBRef
    private Offer offer;
    private LocalDateTime visitDate;
    private OrderStatus orderStatus;
    private String comment;

}
