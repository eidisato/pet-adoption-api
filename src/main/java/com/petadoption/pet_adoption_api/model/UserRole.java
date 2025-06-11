package com.petadoption.pet_adoption_api.model;

public enum UserRole {
    ADMIN("admin");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }


}
