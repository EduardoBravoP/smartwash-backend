package com.smartwash.backend.controller;

import com.smartwash.backend.model.Services;
import com.smartwash.backend.service.ServiceService;
import com.smartwash.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {
    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }
    @GetMapping
    public List<Services> getAllServices() {
        return serviceService.getAllServices();
    }

    @PostMapping
    public Services createService(@RequestBody Services service) {
        return serviceService.saveService(service);
    }
}
