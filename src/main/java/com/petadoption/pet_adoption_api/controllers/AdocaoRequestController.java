package com.petadoption.pet_adoption_api.controllers;

import com.petadoption.pet_adoption_api.dtos.AdocaoRecordDto;
import com.petadoption.pet_adoption_api.model.AdocaoRequest;
import com.petadoption.pet_adoption_api.model.Pet;
import com.petadoption.pet_adoption_api.repositories.PetRepository;
import com.petadoption.pet_adoption_api.services.AdocaoRequestService;
import com.petadoption.pet_adoption_api.services.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/requests")
@CrossOrigin(origins = "*")
public class AdocaoRequestController {

    @Autowired
    private AdocaoRequestService adocaoRequestService;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private EmailService emailService;

    @GetMapping
    public List<AdocaoRequest> listarRequests() {
        return adocaoRequestService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdocaoRequest> buscarRequest(@PathVariable UUID id) {
        return adocaoRequestService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> criarRequest(@RequestBody @Valid AdocaoRecordDto adocaoDto) {
        return adocaoRequestService.salvar(adocaoDto.nomeAdotante(), adocaoDto.email(), adocaoDto.idPet())
                .<ResponseEntity<?>>map(request -> {
                    String nomePet = petRepository.findById(adocaoDto.idPet())
                            .map(Pet::getName)
                            .orElse("o pet selecionado");

                    emailService.enviarConfirmacaoAdocao(
                            adocaoDto.email(),
                            nomePet,
                            adocaoDto.nomeAdotante()
                    );

                    return ResponseEntity.ok(request);
                })
                .orElseGet(() -> ResponseEntity.badRequest().body("Pet não encontrado"));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRequest(@PathVariable UUID id) {
        adocaoRequestService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
