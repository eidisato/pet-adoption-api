package com.petadoption.pet_adoption_api.dtos;

import com.petadoption.pet_adoption_api.model.UserRole;

public record RegisterRecordDto(String login, String password, UserRole role) {
}
