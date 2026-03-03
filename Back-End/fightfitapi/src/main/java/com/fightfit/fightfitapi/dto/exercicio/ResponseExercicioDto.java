package com.fightfit.fightfitapi.dto.exercicio;

import java.util.UUID;

public record ResponseExercicioDto(UUID id, String nome, int serie, int repeticoes, double carga, UUID id_treino)  {

}
