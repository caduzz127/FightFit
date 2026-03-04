package com.fightfit.fightfitapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ManyToAny;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "tb_treino")
public class TreinoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "nome_treino")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_usuarios")
    private UsuarioModel usuario;

    @OneToMany(mappedBy = "treinos", cascade = CascadeType.REMOVE)
    private List<ExercicioModel> exercicios;
}
