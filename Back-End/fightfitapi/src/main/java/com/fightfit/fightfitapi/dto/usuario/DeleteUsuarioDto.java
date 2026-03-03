package com.fightfit.fightfitapi.dto.usuario;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DeleteUsuarioDto(@NotNull UUID id) {

}
