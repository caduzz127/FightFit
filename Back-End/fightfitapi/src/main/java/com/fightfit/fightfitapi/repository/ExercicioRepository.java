package com.fightfit.fightfitapi.repository;

import com.fightfit.fightfitapi.model.ExercicioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ExercicioRepository extends JpaRepository<ExercicioModel, UUID> {


    @Query(value = "Select * from tb_exercicio where nome ILIKE :nome",nativeQuery = true)
    public List<ExercicioModel> findByNome(@Param("nome") String nome);
}
