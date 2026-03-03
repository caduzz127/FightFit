package com.fightfit.fightfitapi.repository;

import com.fightfit.fightfitapi.model.ExercicioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExercicioRepository extends JpaRepository<ExercicioModel, UUID> {
}
