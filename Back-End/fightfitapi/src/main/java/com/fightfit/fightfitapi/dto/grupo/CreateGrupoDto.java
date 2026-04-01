package com.fightfit.fightfitapi.dto.grupo;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateGrupoDto(@NotNull UUID idUsuario, @NotNull String nome, @NotNull String senha) {
}
