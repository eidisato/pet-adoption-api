package com.petadoption.pet_adoption_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_PET")
public class Pet implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    @Setter
    @Getter
    private UUID idPet;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private String tipo;

    @Setter
    @Getter
    private int idade;

    @Setter
    @Getter
    private String porte;

    @Setter
    @Getter
    private String descricao;

    @Setter
    @Getter
    private String imagemUrl;
}