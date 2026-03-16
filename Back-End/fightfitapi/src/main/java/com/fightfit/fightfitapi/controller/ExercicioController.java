package com.fightfit.fightfitapi.controller;


import com.fightfit.fightfitapi.dto.exercicio.CreateExercicioDto;
import com.fightfit.fightfitapi.dto.exercicio.ResponseAllExerciciosDto;
import com.fightfit.fightfitapi.dto.exercicio.ResponseExercicioDto;
import com.fightfit.fightfitapi.dto.exercicio.UpdateExercicioDto;
import com.fightfit.fightfitapi.model.ExercicioModel;
import com.fightfit.fightfitapi.service.ExercicioService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/fightfit/exercicios")
@CrossOrigin(origins = "*")
public class ExercicioController {

    @Autowired
    private ExercicioService exercicioService;


    @PostMapping("/cadastrarExercicios")
    public ResponseEntity<ResponseExercicioDto> salvarExercicio(@RequestBody CreateExercicioDto  createExercicioDto){
        ExercicioModel exercicioModel = exercicioService.salvarExercicio(createExercicioDto);
        ResponseExercicioDto responseExercicioDto = new ResponseExercicioDto(
                exercicioModel.getId(),
                exercicioModel.getNome(),
                exercicioModel.getSerie(),
                exercicioModel.getRepeticoes(),
                exercicioModel.getCarga(),
                exercicioModel.getTreinos().getId()
        );
        return ResponseEntity.ok().body(responseExercicioDto);
    }


    @PutMapping("/atualizarExercicios")
    public ResponseEntity<ResponseExercicioDto>  atualizarTreinos(@RequestBody UpdateExercicioDto  updateExercicioDto){
        ExercicioModel exercicio = exercicioService.atualizarExercicio(updateExercicioDto);

        ResponseExercicioDto responseExercicioDto = new ResponseExercicioDto(
                exercicio.getId(),
                exercicio.getNome(),
                exercicio.getSerie(),
                exercicio.getRepeticoes(),
                exercicio.getCarga(),
                exercicio.getTreinos().getId()
        );
        return ResponseEntity.ok().body(responseExercicioDto);
    }

    @GetMapping("/buscarExercicios/{nome}")
    public ResponseEntity<ResponseAllExerciciosDto> buscarExercicio(@PathVariable String nome){

        List<ExercicioModel> listaDeExercicios = exercicioService.buscarExercicioPorNome(nome);
        List<ResponseExercicioDto> responseExercicioDtos = new ArrayList<>();


        for( int i =0; i<listaDeExercicios.size(); i++ ){
            ResponseExercicioDto responseExercicioDto = new ResponseExercicioDto(
                    listaDeExercicios.get(i).getId(),
                    listaDeExercicios.get(i).getNome(),
                    listaDeExercicios.get(i).getSerie(),
                    listaDeExercicios.get(i).getRepeticoes(),
                    listaDeExercicios.get(i).getCarga(),
                    listaDeExercicios.get(i).getTreinos().getId()
            );
            responseExercicioDtos.add(responseExercicioDto);
        }

        ResponseAllExerciciosDto responseAllExerciciosDto =  new ResponseAllExerciciosDto(responseExercicioDtos);

        return ResponseEntity.ok().body(responseAllExerciciosDto);
    }


    @DeleteMapping("/deletarExercicios/{id}")
    public ResponseEntity<ResponseAllExerciciosDto> deletarExercicio(@PathVariable UUID id){
        exercicioService.deleteExercicio(id);

        return ResponseEntity.noContent().build();
    }
}
