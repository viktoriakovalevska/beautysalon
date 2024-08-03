package com.example.beautysalon.services;

import com.example.beautysalon.dto.OrderRequestDTO;
import com.example.beautysalon.dto.OrderResponseDTO;
import com.example.beautysalon.entities.Order;
import com.example.beautysalon.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final OfferService offerService;

    public OrderResponseDTO addOrder(OrderRequestDTO orderRequestDTO) throws Exception {
        Order order = Order.builder()
                .client(userService.getUser(orderRequestDTO.getClientId()))
                .master(userService.getUser(orderRequestDTO.getMastertId()))
                .offer(offerService.getOfferById(orderRequestDTO.getOfferId()))
                .visitDate(orderRequestDTO.getVisitDate())
                .orderStatus(orderRequestDTO.getOrderStatus())
                .comment(orderRequestDTO.getComment())
                .build();
        return new OrderResponseDTO(orderRepository.save(order));
    }

    public Order getOrderById(String id) throws Exception {
        return orderRepository.findById(id)
                .orElseThrow(() -> new Exception(String.format("order with id %s is not found in DB", id)));
    }

    public OrderResponseDTO editOrder(OrderRequestDTO orderRequestDTO, String id) throws Exception {
        Order order = getOrderById(id).toBuilder()
                .client(userService.getUser(orderRequestDTO.getClientId()))
                .master(userService.getUser(orderRequestDTO.getMastertId()))
                .offer(offerService.getOfferById(orderRequestDTO.getOfferId()))
                .visitDate(orderRequestDTO.getVisitDate())
                .orderStatus(orderRequestDTO.getOrderStatus())
                .comment(orderRequestDTO.getComment())
                .build();
        return new OrderResponseDTO(orderRepository.save(order));
    }

    public List<OrderResponseDTO> findByClient(Long userId) throws Exception {
        return orderRepository.findByClient(userService.getUser(userId)).stream()
                .map(val -> new OrderResponseDTO(val))
                .toList();
    }

    public List<OrderResponseDTO> findByMaster(Long userId) throws Exception {
        return orderRepository.findByMaster(userService.getUser(userId)).stream()
                .map(val -> new OrderResponseDTO(val))
                .toList();
    }
}
