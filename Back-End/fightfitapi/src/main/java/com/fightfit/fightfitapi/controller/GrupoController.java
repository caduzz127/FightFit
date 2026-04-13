package com.fightfit.fightfitapi.controller;


import com.fightfit.fightfitapi.dto.grupo.*;
import com.fightfit.fightfitapi.model.GrupoModel;
import com.fightfit.fightfitapi.model.UsuarioModel;
import com.fightfit.fightfitapi.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fightfit/grupos")
public class GrupoController {

    @Autowired
    GrupoService grupoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<ResponseGrupoAllDto> adicionarGrupo(@RequestBody CreateGrupoDto createGrupoDto) {
        GrupoModel grupoCriado = grupoService.criarGrupo(createGrupoDto);
        List<UsuarioGrupoDto> usuarioModels = grupoCriado.getUsuarios().stream().map( usuarioGrupo ->
                new UsuarioGrupoDto(
                        usuarioGrupo.getUsuario().getId(),
                        usuarioGrupo.getUsuario().getNome(),
                        usuarioGrupo.getCargo()
                )
        ).toList();
        ResponseGrupoAllDto responseGrupoAllDto = new ResponseGrupoAllDto(grupoCriado.getId(), grupoCriado.getNome(), usuarioModels);

        return ResponseEntity.ok().body(responseGrupoAllDto);
    }


    @PostMapping("/entrar")
    public ResponseEntity<ResponseGrupoAllDto> entrarGrupo(@RequestBody JoinGrupoDto joinGrupoDto) {
        GrupoModel grupoCriado = grupoService.entrarGrupo(joinGrupoDto);
        List<UsuarioGrupoDto> usuarioModels = grupoCriado.getUsuarios().stream().map( usuarioGrupo ->
                new UsuarioGrupoDto(
                        usuarioGrupo.getUsuario().getId(),
                        usuarioGrupo.getUsuario().getNome(),
                        usuarioGrupo.getCargo()
                )
        ).toList();
        ResponseGrupoAllDto responseGrupoAllDto = new ResponseGrupoAllDto(grupoCriado.getId(), grupoCriado.getNome(), usuarioModels);

        return ResponseEntity.ok().body(responseGrupoAllDto);
    }
}
