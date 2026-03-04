package com.fightfit.fightfitapi.dto.usuario;

import com.fightfit.fightfitapi.dto.treino.ResponseTreinoDto;

import java.util.ArrayList;
import java.util.UUID;

public record ResponseAllUsuario(UUID id, String nome, String senha, ArrayList<ResponseTreinoDto> listaDeTreinos) {
}
