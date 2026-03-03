package com.fightfit.fightfitapi.dto.usuario;

import com.fightfit.fightfitapi.model.TreinoModel;

import java.util.ArrayList;
import java.util.UUID;

public record ResponseUsuarioDto(UUID id, String nome, String senha, ArrayList<TreinoModel> listaDeTreinos)  {

}
