package com.fightfit.fightfitapi.controller;

import com.fightfit.fightfitapi.dto.treino.*;
import com.fightfit.fightfitapi.model.TreinoModel;
import com.fightfit.fightfitapi.service.TreinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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


    @DeleteMapping("/deletarTreino/{id}")
    public ResponseEntity<ResponseTreinoDto> deletarTreino(@PathVariable("id") UUID idTreino){
        treinoService.deletarTreino(idTreino);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscarTreinosPorNome/{nome}")
    public ResponseEntity<ResponseTreinoByNomeDto> buscarTreinosPorNome(@PathVariable("nome") String nome){
        List<TreinoModel> treinoModels = treinoService.buscarTreinoPorNome(nome);
        List<ResponseTreinoDto> responseTreinoDtos = new ArrayList<>();
        for(int i =0;  i<treinoModels.size(); i++){
            ResponseTreinoDto responseTreinoDto = new ResponseTreinoDto(
                    treinoModels.get(i).getId(),
                    treinoModels.get(i).getNome(),
                    treinoModels.get(i).getUsuario().getId()
            );
            responseTreinoDtos.add(responseTreinoDto);
        }
        ResponseTreinoByNomeDto  responseTreinosDto = new ResponseTreinoByNomeDto(
                responseTreinoDtos
        );
        return  ResponseEntity.status(HttpStatus.OK).body(responseTreinosDto);
    }





}
