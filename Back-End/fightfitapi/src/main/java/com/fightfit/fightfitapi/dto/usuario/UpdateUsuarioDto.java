package com.fightfit.fightfitapi.dto.usuario;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UpdateUsuarioDto(@NotNull UUID id, @NotNull String nome, @NotNull String senha) {
}
