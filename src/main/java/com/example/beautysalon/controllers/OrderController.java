package com.example.beautysalon.controllers;

import com.example.beautysalon.common.Response;
import com.example.beautysalon.dto.OrderRequestDTO;
import com.example.beautysalon.dto.OrderResponseDTO;
import com.example.beautysalon.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("admin/order/add")
    public Response<OrderResponseDTO> addOrder(@RequestBody OrderRequestDTO registrDTO) throws Exception {
        return Response.createSuccessfulResponseEntity(orderService.addOrder(registrDTO));
    }
    @GetMapping("admin/orders/get/client/{id}")
    public Response<List<OrderResponseDTO>> getByClient(@PathVariable(value = "id") Long id) throws Exception {
        return Response.createSuccessfulResponseEntity(orderService.findByClient(id));
    }
    @GetMapping("admin/orders/get/master/{id}")
    public Response<List<OrderResponseDTO>> getByMaster(@PathVariable(value = "id") Long id) throws Exception {
        return Response.createSuccessfulResponseEntity(orderService.findByMaster(id));
    }
    @PutMapping("admin/order/edit/{id}")
    public Response<OrderResponseDTO> editOrder(@RequestBody OrderRequestDTO registrDTO, @PathVariable(value = "id") String id) throws Exception {
        return Response.createSuccessfulResponseEntity(orderService.editOrder(registrDTO, id));
    }
}
