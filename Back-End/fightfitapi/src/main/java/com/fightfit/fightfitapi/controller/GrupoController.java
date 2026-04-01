package com.fightfit.fightfitapi.controller;


import com.fightfit.fightfitapi.dto.grupo.CreateGrupoDto;
import com.fightfit.fightfitapi.dto.grupo.ResponseGrupoDto;
import com.fightfit.fightfitapi.model.GrupoModel;
import com.fightfit.fightfitapi.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fightfit/grupos")
public class GrupoController {

    @Autowired
    GrupoService grupoService;

    @PostMapping("/cadastrarGrupos")
    public ResponseEntity<ResponseGrupoDto> adicionarGrupo(@RequestBody CreateGrupoDto createGrupoDto) {
        GrupoModel grupoCriado = grupoService.criarGrupo(createGrupoDto);
        ResponseGrupoDto response = new ResponseGrupoDto(
                grupoCriado.getId(),
                grupoCriado.getNome()
        );
        return ResponseEntity.ok().body(response);
    }
}
