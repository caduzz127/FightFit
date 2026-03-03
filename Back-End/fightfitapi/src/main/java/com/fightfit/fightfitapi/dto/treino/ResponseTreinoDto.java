package com.fightfit.fightfitapi.dto.treino;

import com.fightfit.fightfitapi.model.ExercicioModel;

import java.util.ArrayList;
import java.util.UUID;

public record ResponseTreinoDto(UUID id, String nome, UUID id_usuario, ArrayList<ExercicioModel> listaExercicio)  {

}
