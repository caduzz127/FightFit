package com.fightfit.fightfitapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_grupo")
public class GrupoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "nome_do_grupo",unique = true)
    private String nome;

    @Column(name = "senha_do_grupo")
    private String senha;

    @Column
    private String cargo;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UsuarioModel usuario;


}
