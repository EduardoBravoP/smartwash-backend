package com.smartwash.backend.service;

import com.smartwash.backend.model.CreateOrderRequestDTO;
import com.smartwash.backend.model.Order;
import com.smartwash.backend.repository.OrderRepository;
import com.smartwash.backend.repository.MachineRepository;
import com.smartwash.backend.repository.ServiceRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final MachineRepository machineRepository;
    private final ServiceRepository serviceRepository;

    public OrderService(OrderRepository orderRepository, MachineRepository machineRepository, ServiceRepository serviceRepository) {
        this.orderRepository = orderRepository;
        this.machineRepository = machineRepository;
        this.serviceRepository = serviceRepository;
    }

    public Order createOrder(CreateOrderRequestDTO request) {
        Order order = new Order();
        
        order.setMachine(machineRepository.findById(request.machineId())
                .orElseThrow(() -> new RuntimeException("Machine not found")));
                
        order.setService(serviceRepository.findById(request.serviceId())
                .orElseThrow(() -> new RuntimeException("Service not found")));
                
        order.setStartTime(Timestamp.valueOf(request.startTime()));
        order.setEndTime(Timestamp.valueOf(request.endTime()));
        order.setCreatedAt(LocalDate.now());

        return orderRepository.save(order);
    }
}