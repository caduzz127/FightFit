package com.fightfit.fightfitapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "tb_grupo_usuarios")
public class GrupoUsuariosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @ManyToOne
    @JoinColumn(name = "id_grupo")
    private GrupoModel grupo;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UsuarioModel usuario;


    @OneToMany(mappedBy = "grupoUsuario")
    private List<RankingModel> ranking;
}
