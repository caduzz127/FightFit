package com.fightfit.fightfitapi.dto.usuario;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ResponseLoginDto(UUID id,String nome) {
}
