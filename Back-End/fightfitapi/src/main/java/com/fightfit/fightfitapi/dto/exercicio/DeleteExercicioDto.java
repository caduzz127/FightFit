package com.fightfit.fightfitapi.dto.exercicio;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DeleteExercicioDto(@NotNull UUID id) {

}
