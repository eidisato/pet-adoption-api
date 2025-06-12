package com.petadoption.pet_adoption_api.services;

import com.petadoption.pet_adoption_api.model.Pet;
import com.petadoption.pet_adoption_api.repositories.AdocaoRequestRepository;
import com.petadoption.pet_adoption_api.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private AdocaoRequestRepository adocaoRequestRepository;


    public List<Pet> listar() {
        return petRepository.findAll();
    }

    public Optional<Pet> buscarPorId(UUID idPet) {
        return petRepository.findById(idPet);
    }

    public Pet salvar(Pet pet) {
        return petRepository.save(pet);
    }

    public void deletar(UUID idPet) {
        adocaoRequestRepository.deleteAllByPet_IdPet(idPet);
        petRepository.deleteById(idPet);
    }
}
