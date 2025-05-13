package com.smartwash.backend.service;

import com.smartwash.backend.model.Machine;
import com.smartwash.backend.model.Services;
import com.smartwash.backend.repository.MachineRepository;
import com.smartwash.backend.repository.OrderRepository;
import com.smartwash.backend.repository.ServiceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MachineService {
    private final MachineRepository machineRepository;
    private final OrderRepository orderRepository;
    private final ServiceRepository serviceRepository;

    public MachineService(MachineRepository machineRepository, OrderRepository orderRepository, ServiceRepository serviceRepository) {
        this.machineRepository = machineRepository;
        this.orderRepository = orderRepository;
        this.serviceRepository = serviceRepository;
    }

    public List<Machine> getAllMachines() {
        return machineRepository.findAll();
    }

    public Machine getMachineById(UUID id) {
        return machineRepository.findById(id).orElse(null);
    }

    public Machine saveMachine(Machine machine) {
        return machineRepository.save(machine);
    }

    public Machine updateMachine(Machine machine) {
        return machineRepository.save(machine);
    }

    public void deleteMachine(UUID id) {
        machineRepository.deleteById(id);
    }

    public List<String> getAvailableHours(UUID machineId, UUID serviceId, LocalDateTime date) {
        Optional<Services> service = serviceRepository.findById(serviceId);

        if(service.isPresent()) {
            var serviceDuration = service.get().getDuration();

            List<String> horarios = new ArrayList<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

            LocalTime horaInicio = LocalTime.parse("08:00", formatter);
            LocalTime horaFim = LocalTime.parse("22:00", formatter);

            while (!horaInicio.plusMinutes(serviceDuration).isAfter(horaFim)) {
                horarios.add(horaInicio.format(formatter));
                horaInicio = horaInicio.plusMinutes(serviceDuration);
            }

            return horarios;
        }

        return null;
    }
}
