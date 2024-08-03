package com.example.beautysalon.dto;

import com.example.beautysalon.common.OrderStatus;
import com.example.beautysalon.entities.Offer;
import com.example.beautysalon.entities.Order;
import com.example.beautysalon.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
    private String id;
    private User client;
    private User master;
    private Offer offer;
    private LocalDateTime visitDate;
    private OrderStatus orderStatus;
    private String comment;

    public OrderResponseDTO(Order order) {
        this.id = order.getId();
        this.client = order.getClient();
        this.master = order.getMaster();
        this.offer = order.getOffer();
        this.visitDate = order.getVisitDate();
        this.orderStatus = order.getOrderStatus();
        this.comment = order.getComment();
    }
}
