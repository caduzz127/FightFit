package com.fightfit.fightfitapi.dto.grupo;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record JoinGrupoDto(@NotNull String nomeGrupo, @NotNull String senhaGrupo,@NotNull UUID id_grupo, @NotNull UUID id_usuario){
}
