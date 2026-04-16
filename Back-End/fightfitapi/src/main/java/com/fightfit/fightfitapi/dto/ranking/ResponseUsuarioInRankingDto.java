package com.fightfit.fightfitapi.dto.ranking;

import java.util.UUID;

public record ResponseUsuarioInRankingDto(UUID id, String nome, double carga) {
}
