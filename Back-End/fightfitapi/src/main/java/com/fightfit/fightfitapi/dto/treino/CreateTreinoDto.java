package com.fightfit.fightfitapi.dto.treino;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateTreinoDto(@NotNull String nome, @NotNull UUID idUsuario) {

}
