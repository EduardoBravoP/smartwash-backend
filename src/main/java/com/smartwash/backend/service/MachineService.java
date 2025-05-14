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

        if (service.isPresent()) {
            var serviceDuration = service.get().getDuration();
            var scheduledOrders = orderRepository.findAllByStartTimeDate(date.toLocalDate());
        
            List<String> availableHours = new ArrayList<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

            LocalTime startTime = LocalTime.parse("08:00", formatter);
            LocalTime endTime = LocalTime.parse("22:00", formatter);

            while (!startTime.plusMinutes(serviceDuration).isAfter(endTime)) {
                LocalDateTime proposedStartTime = date.with(startTime);
                LocalDateTime proposedEndTime = proposedStartTime.plusMinutes(serviceDuration);
            
                boolean isTimeSlotAvailable = scheduledOrders.stream()
                    .noneMatch(order -> {
                        LocalDateTime orderStart = order.getStartTime().toLocalDateTime();
                        LocalDateTime orderEnd = order.getEndTime().toLocalDateTime();
                    
                        // Check if the proposed time slot overlaps with any existing order
                        return order.getMachine().getId().equals(machineId) && 
                               !(proposedEndTime.isBefore(orderStart) || proposedStartTime.isAfter(orderEnd));
                    });

                if (isTimeSlotAvailable) {
                    availableHours.add(startTime.format(formatter));
                }
            
                startTime = startTime.plusMinutes(serviceDuration);
            }

            return availableHours;
        }

        return null;
    }
}