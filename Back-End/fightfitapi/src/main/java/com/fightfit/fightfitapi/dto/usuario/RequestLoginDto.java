package com.fightfit.fightfitapi.dto.usuario;

import jakarta.validation.constraints.NotNull;

public record RequestLoginDto(@NotNull String nome, @NotNull String senha) {
}
