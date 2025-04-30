package com.smartwash.backend.model;

public enum UserRoles {
    CUSTOMER("customer"),
    ADMIN("admin");

    private String role;
    UserRoles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
