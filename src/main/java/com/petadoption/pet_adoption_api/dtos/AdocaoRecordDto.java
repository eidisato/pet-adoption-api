package com.petadoption.pet_adoption_api.dtos;

import java.util.UUID;

public record AdocaoRecordDto(String nomeAdotante, String email, UUID idPet) {}
