package com.smartwash.backend.controller;

import com.smartwash.backend.model.CreateOrderRequestDTO;
import com.smartwash.backend.model.Order;
import com.smartwash.backend.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequestDTO request) {
        Order newOrder = orderService.createOrder(request);
        return ResponseEntity.ok(newOrder);
    }
}