package com.fightfit.fightfitapi.repository;

import com.fightfit.fightfitapi.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, UUID> {


    @Query(value = "SELECT * FROM tb_usuarios WHERE nome = :nome",nativeQuery = true)
    public Optional<UsuarioModel> findByName(@Param("nome")String nome);

}
