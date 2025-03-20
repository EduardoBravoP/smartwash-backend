package com.smartwash.backend.service;

import com.smartwash.backend.model.Machine;
import com.smartwash.backend.repository.MachineRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MachineService {
    private final MachineRepository machineRepository;

    public MachineService(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
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
}
