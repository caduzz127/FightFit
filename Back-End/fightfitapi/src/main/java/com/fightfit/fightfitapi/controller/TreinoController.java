package com.fightfit.fightfitapi.controller;

import com.fightfit.fightfitapi.dto.treino.CreateTreinoDto;
import com.fightfit.fightfitapi.dto.treino.ResponseTreinoDto;
import com.fightfit.fightfitapi.dto.treino.UpdateTreinoDto;
import com.fightfit.fightfitapi.dto.usuario.CreateUsuarioDto;
import com.fightfit.fightfitapi.dto.usuario.ResponseUsuarioDto;
import com.fightfit.fightfitapi.model.TreinoModel;
import com.fightfit.fightfitapi.model.UsuarioModel;
import com.fightfit.fightfitapi.service.TreinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/fightfit/treinos")
@CrossOrigin("*")
public class TreinoController {


    @Autowired
    TreinoService treinoService;

    @PostMapping("/cadastrarTreino")
    public ResponseEntity<ResponseTreinoDto> salvarTreino(@RequestBody CreateTreinoDto createTreinoDto){
        TreinoModel treinoModel = treinoService.salvarTreino(createTreinoDto);

        ResponseTreinoDto responseTreinoDto = new ResponseTreinoDto(
                treinoModel.getId(),
                treinoModel.getNome(),
                treinoModel.getUsuario().getId()
        );
        return ResponseEntity.status(HttpStatus.OK).body(responseTreinoDto);
    }


    @PutMapping("/atualizarTreino")
    public ResponseEntity<ResponseTreinoDto> atualizarTreino(@RequestBody UpdateTreinoDto updateTreinoDto){
        TreinoModel treinoModel = treinoService.atualizarTreino(updateTreinoDto);
        ResponseTreinoDto responseTreinoDto = new ResponseTreinoDto(
                treinoModel.getId(),
                treinoModel.getNome(),
                treinoModel.getUsuario().getId()
        );
        return ResponseEntity.ok().body(responseTreinoDto);
    }



}
