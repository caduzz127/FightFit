package com.fightfit.fightfitapi.dto.ranking;

import com.fightfit.fightfitapi.dto.grupo.ResponseGrupoDto;
import com.fightfit.fightfitapi.dto.usuario.ResponseLoginDto;
import com.fightfit.fightfitapi.dto.usuario.ResponseUsuarioDto;
import com.fightfit.fightfitapi.model.GrupoModel;
import com.fightfit.fightfitapi.repository.RankingUsuarioRepository;

import java.util.List;
import java.util.UUID;

public record ResponseRankingDto(UUID id, String nome, ResponseGrupoDto grupo, List<ResponseUsuarioInRankingDto> usuarios) {
}
