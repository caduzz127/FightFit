package com.fightfit.fightfitapi.dto.exercicio;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UpdateExercicioDto(@NotNull UUID id, @NotNull String nome, @NotNull int serie,
                                 @NotNull int repeticoes, @NotNull double carga, @NotNull UUID id_treino) {
}
