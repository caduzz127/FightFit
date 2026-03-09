package com.fightfit.fightfitapi.repository;

import com.fightfit.fightfitapi.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, UUID> {


    @Query(value = "SELECT * FROM tb_usuario WHERE nome_de_usuario = :nome",nativeQuery = true)
    public Optional<UsuarioModel> findByName(@Param("nome")String nome);

    @Query(value = "SELECT DISTINCT u FROM tb_usuario u JOIN FETCH u.treinos WHERE u.nome_de_usuario = :nome",nativeQuery = true)
    public Optional<UsuarioModel> findByNameUsuarioAndTreino(@Param("nome")String nome);

    @Query(value = "select * from tb_usuario where nome_de_usuario = :nome and senha_usuario = :senha", nativeQuery = true)
    public Optional<UsuarioModel> findByNameAndSenha(@Param("nome")String nome, @Param("senha")String senha);

}
