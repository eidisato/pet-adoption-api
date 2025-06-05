package com.petadoption.pet_adoption_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_ADOCAO")
public class AdocaoRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_adocao", columnDefinition = "uuid", updatable = false, nullable = false)
    @Setter @Getter
    private UUID idAdocao;

    @Setter @Getter
    private String nomeAdotante;

    @Setter @Getter
    private String email;

    @Setter @Getter
    @ManyToOne
    @JoinColumn(name = "id_pet", nullable = false)
    private Pet pet;
}
