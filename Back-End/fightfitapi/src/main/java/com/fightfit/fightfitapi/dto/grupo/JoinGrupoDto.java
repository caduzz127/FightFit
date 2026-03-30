package com.fightfit.fightfitapi.dto.grupo;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record JoinGrupoDto(@NotNull String nome, @NotNull String senha,@NotNull UUID id_grupo, @NotNull UUID id_usuario){
}
