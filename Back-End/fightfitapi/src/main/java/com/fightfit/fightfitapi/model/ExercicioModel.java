package com.fightfit.fightfitapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "tb_exercicio")
public class ExercicioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private int serie;

    @Column(nullable = false)
    private int repeticoes;

    @Column(nullable = false)
    private double carga;

    @ManyToOne
    @JoinColumn(name = "id_treino")
    @JsonIgnore
    private TreinoModel treinos;

}
