package com.fightfit.fightfitapi.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_rankings")
public class RankingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "nome_do_ranking")
    private String nomeDoRanking;



    @ManyToOne()
    @JoinColumn(name = "grupo")
    private GrupoModel grupo;

    @OneToMany(mappedBy = "ranking")
    private List<RankingUsuarioModel> usuarios;

}
