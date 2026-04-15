package com.fightfit.fightfitapi.dto.ranking;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateRankingDto(@NotNull String nomeDoRanking, @NotNull double cargaRanking, @NotNull UUID idUsuario, @NotNull UUID idGrupo) {
}
