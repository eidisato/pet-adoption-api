package com.petadoption.pet_adoption_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_PET")
public class Pet implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
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
    private String raca;

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

    @Setter
    @Getter
    @Column(name = "is_available")
    private Boolean isAvailable = true;
}