package com.fightfit.fightfitapi.dto.treino;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;


public record UpdateTreinoDto(@NotNull UUID id, @NotNull String nome) {

}
