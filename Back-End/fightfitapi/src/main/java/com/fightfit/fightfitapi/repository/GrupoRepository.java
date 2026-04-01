package com.fightfit.fightfitapi.repository;

import com.fightfit.fightfitapi.model.GrupoModel;
import com.fightfit.fightfitapi.model.GrupoUsuariosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface GrupoRepository extends JpaRepository<GrupoModel,UUID> {

    @Query(value = "select * from tb_grupo where nome_do_grupo = :nome", nativeQuery = true)
    public Optional<GrupoModel> findByName(@Param("nome") String nome);

    @Query(value = "select * from tb_grupo where nome_do_grupo = :nome and senha_do_grupo = :senha", nativeQuery = true)
    public Optional<GrupoModel> findByNameAndSenha(@Param("nome")String nome, @Param("senha")String senha);

    @Query(value = "delete * from tb_grupo where nome_do_grupo = :nome", nativeQuery = true)
    public GrupoModel deleteByName(@Param("nome")String nome);
}
