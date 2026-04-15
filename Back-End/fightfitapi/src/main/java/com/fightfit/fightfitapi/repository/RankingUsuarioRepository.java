package com.fightfit.fightfitapi.repository;

import com.fightfit.fightfitapi.model.RankingModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RankingUsuarioRepository extends JpaRepository<RankingModel, UUID> {
}
