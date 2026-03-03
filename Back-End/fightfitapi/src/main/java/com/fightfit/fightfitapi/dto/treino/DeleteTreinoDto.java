package com.fightfit.fightfitapi.dto.treino;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DeleteTreinoDto(@NotNull UUID id) {

}
