package com.fightfit.fightfitapi.dto.usuario;

import java.util.UUID;

public record ResponseUsuarioDto(UUID id, String nome, String senha)  {

}
