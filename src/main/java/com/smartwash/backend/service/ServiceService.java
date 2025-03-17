package com.smartwash.backend.service;

import com.smartwash.backend.model.Services;
import com.smartwash.backend.model.User;
import com.smartwash.backend.repository.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService {
    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<Services> getAllServices() {
        return serviceRepository.findAll();
    }

    public Services saveService(Services service) {
        return serviceRepository.save(service);
    }
}
