package com.fightfit.fightfitapi.repository;

import com.fightfit.fightfitapi.model.RankingModel;
import com.fightfit.fightfitapi.model.RankingUsuarioModel;
import com.fightfit.fightfitapi.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface RankingUsuarioRepository extends JpaRepository<RankingUsuarioModel, UUID> {

    @Query(value = "select * from tb_ranking_usuarios where ranking = :id", nativeQuery = true)
    public List<RankingUsuarioModel> findAllByRanking(@Param("id") UUID id);
}
