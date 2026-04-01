package com.fightfit.fightfitapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
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

    @OneToMany(mappedBy = "grupo")
    private List<GrupoUsuariosModel> usuario;





}
