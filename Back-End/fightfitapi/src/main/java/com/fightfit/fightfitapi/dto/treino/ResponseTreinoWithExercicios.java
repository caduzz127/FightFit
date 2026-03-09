package com.fightfit.fightfitapi.dto.treino;

import com.fightfit.fightfitapi.dto.exercicio.ResponseExercicioDto;

import java.util.List;
import java.util.UUID;

public record ResponseTreinoWithExercicios(UUID id, String nome, UUID id_usuario, List<ResponseExercicioDto> listaDeExercicios) {
}
