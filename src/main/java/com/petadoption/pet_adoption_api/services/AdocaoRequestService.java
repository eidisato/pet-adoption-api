package com.petadoption.pet_adoption_api.services;

import com.petadoption.pet_adoption_api.model.AdocaoRequest;
import com.petadoption.pet_adoption_api.model.Pet;
import com.petadoption.pet_adoption_api.repositories.AdocaoRequestRepository;
import com.petadoption.pet_adoption_api.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdocaoRequestService {

    @Autowired
    private AdocaoRequestRepository adocaoRequestRepository;

    @Autowired
    private PetRepository petRepository;

    public List<AdocaoRequest> listar() {
        return adocaoRequestRepository.findAll();
    }

    public Optional<AdocaoRequest> buscarPorId(UUID idAdocaoRequest) {
        return adocaoRequestRepository.findById(idAdocaoRequest);
    }

    public Optional<AdocaoRequest> salvar(String nomeAdotante, String email, UUID idPet) {
        Optional<Pet> petOpt = petRepository.findById(idPet);
        if (petOpt.isEmpty()) return Optional.empty();

        AdocaoRequest request = new AdocaoRequest();
        request.setNomeAdotante(nomeAdotante);
        request.setEmail(email);
        request.setPet(petOpt.get());
        return Optional.of(adocaoRequestRepository.save(request));
    }

    public void deletar(UUID idAdocaoRequest) {
        adocaoRequestRepository.deleteById(idAdocaoRequest);
    }
}
