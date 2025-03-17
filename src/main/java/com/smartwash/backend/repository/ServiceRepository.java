package com.smartwash.backend.repository;

import com.smartwash.backend.model.Services;
import com.smartwash.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ServiceRepository extends JpaRepository<Services, UUID> {
}
