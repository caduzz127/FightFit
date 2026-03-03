package com.fightfit.fightfitapi.repository;

import com.fightfit.fightfitapi.model.TreinoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TreinoRepository extends JpaRepository<TreinoModel, UUID> {
}
