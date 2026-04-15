package com.fightfit.fightfitapi.repository;

import com.fightfit.fightfitapi.model.GrupoUsuariosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GrupoUsuariosRepository extends JpaRepository<GrupoUsuariosModel, UUID> {

    @Query(value = "Select * from tb_grupo_usuarios where id_grupo = :id ", nativeQuery  = true )
    public List<GrupoUsuariosModel>  findByIdGrupo(@Param("id") UUID id);

    @Query(value = "Select * from tb_grupo_usuarios where id_usuario = :id ", nativeQuery = true)
    public Optional<GrupoUsuariosModel> findByUsuario(@Param("id") UUID id);

}
