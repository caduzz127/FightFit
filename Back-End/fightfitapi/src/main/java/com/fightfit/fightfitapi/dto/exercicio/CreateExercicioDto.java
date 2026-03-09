package com.fightfit.fightfitapi.dto.exercicio;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateExercicioDto(@NotNull String nome, @NotNull int repeticoes, @NotNull int serie,
                                 @NotNull double carga, @NotNull UUID idTreino) {

}
