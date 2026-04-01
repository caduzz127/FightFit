package com.fightfit.fightfitapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "tb_grupo_usuarios")
public class GrupoUsuariosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_grupo")
    private GrupoModel grupo;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UsuarioModel usuario;

    @Column
    private String cargo;

    @OneToMany(mappedBy = "grupoUsuario")
    private List<RankingModel> ranking;
}
