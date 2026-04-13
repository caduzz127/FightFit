package com.fightfit.fightfitapi.dto.grupo;

import com.fightfit.fightfitapi.model.UsuarioModel;

import java.util.List;
import java.util.UUID;

public record ResponseGrupoAllDto(UUID id, String nome, List<UsuarioGrupoDto> usuarioGrupoDtos) {
}
