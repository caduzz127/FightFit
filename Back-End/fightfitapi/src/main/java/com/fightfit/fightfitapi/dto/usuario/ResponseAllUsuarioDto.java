package com.fightfit.fightfitapi.dto.usuario;

import com.fightfit.fightfitapi.dto.treino.ResponseTreinoDto;
import com.fightfit.fightfitapi.dto.treino.ResponseTreinoWithExercicios;

import java.util.List;
import java.util.UUID;

public record ResponseAllUsuarioDto(UUID id, String nome, List<ResponseTreinoWithExercicios> listaDeTreinos) {

}
