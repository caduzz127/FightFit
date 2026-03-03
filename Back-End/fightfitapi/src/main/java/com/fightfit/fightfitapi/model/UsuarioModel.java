package com.fightfit.fightfitapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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

    @Column(name = "nome_de_usuario", nullable = false)
    private String nome;

    @Column(name = "senha_usuario", nullable = false)
    private String senha;

    @OneToMany(mappedBy = "usuario")
    private ArrayList<TreinoModel> treinos;
}
