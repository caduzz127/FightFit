package com.fightfit.fightfitapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_usuario")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "nome_de_usuario", nullable = false, unique = true)
    private String nome;

    @Column(name = "senha_usuario", nullable = false)
    private String senha;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<TreinoModel> treinos;

    @OneToMany(mappedBy = "usuario")
    private List<GrupoUsuariosModel> grupos;

    @OneToMany(mappedBy = "usuario")
    private List<RankingUsuarioModel> rankings;

}
