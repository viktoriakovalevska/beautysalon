package com.example.beautysalon.dto;

import com.example.beautysalon.common.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderRequestDTO {
    private Long clientId;
    private Long mastertId;
    private String offerId;
    private LocalDateTime visitDate;
    private OrderStatus orderStatus;
    private String comment;
}
