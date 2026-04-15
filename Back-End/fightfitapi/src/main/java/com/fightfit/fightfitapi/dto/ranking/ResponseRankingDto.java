package com.fightfit.fightfitapi.dto.ranking;

import com.fightfit.fightfitapi.dto.grupo.ResponseGrupoDto;
import com.fightfit.fightfitapi.dto.usuario.ResponseUsuarioDto;
import com.fightfit.fightfitapi.model.GrupoModel;

import java.util.List;
import java.util.UUID;

public record ResponseRankingDto(UUID id, ResponseGrupoDto grupo, List<ResponseUsuarioDto> usuarios) {
}
