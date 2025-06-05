package com.petadoption.pet_adoption_api.controllers;

import com.petadoption.pet_adoption_api.dtos.PetRecordDto;
import com.petadoption.pet_adoption_api.model.Pet;
import com.petadoption.pet_adoption_api.services.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pets")
@CrossOrigin(origins = "http://localhost:3000") // Permitir acesso do React
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping
    public List<Pet> listarPets() {
        return petService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> buscarPet(@PathVariable UUID id) {
        return petService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pet> criarPet(@RequestBody @Valid PetRecordDto petRecordDto) {
        Pet pet = new Pet();
        pet.setName(petRecordDto.name());
        pet.setTipo(petRecordDto.tipo());
        pet.setIdade(petRecordDto.idade());
        pet.setPorte(petRecordDto.porte());
        pet.setDescricao(petRecordDto.descricao());
        pet.setImagemUrl(petRecordDto.imagemUrl());
        return ResponseEntity.status(HttpStatus.CREATED).body(petService.salvar(pet));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pet> atualizarPet(@PathVariable UUID id, @RequestBody @Valid PetRecordDto dto) {
        return petService.buscarPorId(id)
                .map(pet -> {
                    pet.setName(dto.name());
                    pet.setTipo(dto.tipo());
                    pet.setIdade(dto.idade());
                    pet.setPorte(dto.porte());
                    pet.setDescricao(dto.descricao());
                    pet.setImagemUrl(dto.imagemUrl());
                    return ResponseEntity.ok(petService.salvar(pet));
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPet(@PathVariable UUID id) {
        petService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
