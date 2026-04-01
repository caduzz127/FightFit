package com.fightfit.fightfitapi.repository;

import com.fightfit.fightfitapi.model.GrupoUsuariosModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GrupoUsuariosRepository extends JpaRepository<GrupoUsuariosModel, UUID> {
}
