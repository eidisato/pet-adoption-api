package com.petadoption.pet_adoption_api.repositories;

import com.petadoption.pet_adoption_api.model.AdocaoRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdocaoRequestRepository extends JpaRepository<AdocaoRequest, UUID> {
    void deleteByPet_IdPet(UUID idPet);
}
