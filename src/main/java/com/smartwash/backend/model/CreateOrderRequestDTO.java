package com.smartwash.backend.model;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateOrderRequestDTO(
    UUID machineId,
    UUID serviceId,
    LocalDateTime startTime,
    LocalDateTime endTime
) {}