package com.smartwash.backend.controller;

import com.smartwash.backend.model.Machine;
import com.smartwash.backend.model.Services;
import com.smartwash.backend.service.MachineService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/machines")
public class MachineController {
    private final MachineService machineService;

    public MachineController(MachineService machineService) {
        this.machineService = machineService;
    }

    @GetMapping
    public List<Machine> getAllMachines() {
        return machineService.getAllMachines();
    }

    @PostMapping
    public Machine createMachine(@RequestBody Machine machine) {
        machine.setStatus("ACTIVE");
        return machineService.saveMachine(machine);
    }

    @GetMapping("/{id}")
    public Machine getMachineById(@PathVariable UUID id) {
        return machineService.getMachineById(id);
    }

    @PutMapping("/{id}")
    public Machine updateMachine(@PathVariable UUID id, @RequestBody Map<String, String> updates) {
        Machine existingMachine = machineService.getMachineById(id);

        if (updates.containsKey("name")) {
            existingMachine.setName(updates.get("name"));
        }

        if (updates.containsKey("status")) {
            existingMachine.setStatus(updates.get("status"));
        }

        return machineService.updateMachine(existingMachine);
    }

    @DeleteMapping("/{id}")
    public void deleteMachine(@PathVariable UUID id) {
        machineService.deleteMachine(id);
    }

    @PostMapping("/{id}/available-hours")
    public List<String> getAvailableHours(@PathVariable UUID id, @RequestBody Map<String, String> updates) {
        Machine existingMachine = machineService.getMachineById(id);

        return machineService.getAvailableHours(id, UUID.fromString(updates.get("serviceId")), LocalDateTime.parse(updates.get("date")));
    }
}
