package com.fightfit.fightfitapi.repository;

import com.fightfit.fightfitapi.model.TreinoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TreinoRepository extends JpaRepository<TreinoModel, UUID> {

    @Query(value = "SELECT * FROM tb_treino WHERE id_usuarios = :id ORDER BY nome ASC", nativeQuery = true)
    public List<TreinoModel> findTreinoByIdUsuario(@Param("id")UUID uuid);


    @Query(value = "SELECT * FROM tb_treino WHERE nome_treino ILIKE :nome", nativeQuery = true)
    public List<TreinoModel> findTreinoByName(@Param("nome")String nome);
}
