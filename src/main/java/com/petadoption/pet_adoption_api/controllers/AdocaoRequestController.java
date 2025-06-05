package com.petadoption.pet_adoption_api.controllers;

import com.petadoption.pet_adoption_api.dtos.AdocaoRecordDto;
import com.petadoption.pet_adoption_api.model.AdocaoRequest;
import com.petadoption.pet_adoption_api.services.AdocaoRequestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/requests")
@CrossOrigin(origins = "http://localhost:3000") // acesso react
public class AdocaoRequestController {

    @Autowired
    private AdocaoRequestService adocaoRequestService;

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
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().body("Pet não encontrado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRequest(@PathVariable UUID id) {
        adocaoRequestService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
