package com.smartwash.backend.model;

public record RegisterDTO(String email, String password, String name, UserRoles role) {
}
