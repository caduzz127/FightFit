package com.fightfit.fightfitapi.dto.ranking;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateRankingDto(@NotNull String nome, @NotNull double carga, @NotNull UUID idUsuario, @NotNull UUID idGrupo) {
}
