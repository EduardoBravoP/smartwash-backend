package com.smartwash.backend.repository;

import com.smartwash.backend.model.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MachineRepository extends JpaRepository<Machine, UUID> {
}
