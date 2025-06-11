package com.petadoption.pet_adoption_api.dtos;

import jakarta.validation.constraints.NotBlank;

public record PetRecordDto(@NotBlank String name, String tipo, String raca,  int idade, String porte, String descricao, String imagemUrl, boolean isAvailable) {
}
