package com.fightfit.fightfitapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_ranking_usuarios")
public class RankingUsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne()
    @JoinColumn(name = "usuario")
    private UsuarioModel usuario;

    @ManyToOne()
    @JoinColumn(name = "ranking")
    private RankingModel ranking;

    @Column(name = "carga_ranking")
    private double carga;
}
