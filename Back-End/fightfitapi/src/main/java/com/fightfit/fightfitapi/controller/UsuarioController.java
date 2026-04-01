package com.fightfit.fightfitapi.controller;

import com.fightfit.fightfitapi.dto.exercicio.ResponseExercicioDto;
import com.fightfit.fightfitapi.dto.treino.ResponseTreinoDto;
import com.fightfit.fightfitapi.dto.treino.ResponseTreinoWithExercicios;
import com.fightfit.fightfitapi.dto.usuario.*;
import com.fightfit.fightfitapi.model.UsuarioModel;
import com.fightfit.fightfitapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/fightfit/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/cadastrarUsuario")
    public ResponseEntity<ResponseUsuarioDto>salvarUsuario(@RequestBody CreateUsuarioDto createUsuarioDto){
        UsuarioModel usuarioModel = usuarioService.salvarUsuario(createUsuarioDto);
        System.out.println("Salvo com sucesso");
        ResponseUsuarioDto responseUsuarioDto = new ResponseUsuarioDto(
                usuarioModel.getId(),
                usuarioModel.getNome(),
                usuarioModel.getSenha()
        );
        return ResponseEntity.status(HttpStatus.OK).body(responseUsuarioDto);
    }



    @DeleteMapping("/deletarUsuario")
    public ResponseEntity<ResponseUsuarioDto> deletarUsuario(@RequestBody DeleteUsuarioDto deleteUsuarioDto){
        usuarioService.deletarUsuario(deleteUsuarioDto.id());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizarUsuario")
    public ResponseEntity<ResponseUsuarioDto> atualizarUsuario(@RequestBody UpdateUsuarioDto updateUsuarioDto){
        UsuarioModel usuarioModel = usuarioService.atualizarUsuario(updateUsuarioDto);

        ResponseUsuarioDto responseUsuarioDto = new ResponseUsuarioDto(
                usuarioModel.getId(),
                usuarioModel.getNome(),
                usuarioModel.getSenha()

        );
        return ResponseEntity.ok().body(responseUsuarioDto);
    }


    @GetMapping("/buscarUsuarios/{nome}")
    public ResponseEntity<ResponseUsuarioDto> buscarUsuarios(@PathVariable String nome){
        System.out.println("nome recebido: "+nome);
        UsuarioModel usuarioModel = usuarioService.findByNome(nome);


        ResponseUsuarioDto responseUsuarioDto = new ResponseUsuarioDto(
                usuarioModel.getId(),
                usuarioModel.getNome(),
                usuarioModel.getSenha()
        );
        return ResponseEntity.ok().body(responseUsuarioDto);
    }


    //falta a parte dos exercicios
    @GetMapping("/buscarUsuariosComTreinosExercicios/{nome}")
    public ResponseEntity<ResponseAllUsuarioDto> buscarUsuariosComTreino(@PathVariable String nome){
        System.out.println("nome recebido: "+nome);
        UsuarioModel usuarioModel = usuarioService.findByNome(nome);

        List<ResponseTreinoWithExercicios> listaDeTreinos = usuarioModel.getTreinos()
                .stream()
                .map(treino -> new ResponseTreinoWithExercicios(
                        treino.getId(),
                        treino.getNome(),
                        usuarioModel.getId(),
                        treino.getExercicios()
                                .stream()
                                .map(exercicio -> new ResponseExercicioDto(
                                        exercicio.getId(),
                                        exercicio.getNome(),
                                        exercicio.getSerie(),
                                        exercicio.getRepeticoes(),
                                        exercicio.getCarga(),
                                        exercicio.getTreinos().getId()
                                ))
                                .toList()
                ))
                .toList();


        ResponseAllUsuarioDto responseUsuarioDto = new ResponseAllUsuarioDto(
                usuarioModel.getId(),
                usuarioModel.getNome(),
                listaDeTreinos
        );
        return ResponseEntity.ok().body(responseUsuarioDto);
    }




    @PostMapping("/login")
    public ResponseEntity<ResponseLoginDto> login(@RequestBody RequestLoginDto requestLoginDto){
        System.out.println("entrou");
        System.out.println(requestLoginDto.nome()+" "+requestLoginDto.senha());
        UsuarioModel usuarioModel = usuarioService.findByNomeAndSenha(requestLoginDto.nome(),requestLoginDto.senha());
        ResponseLoginDto responseLoginDto = new ResponseLoginDto(
                usuarioModel.getId(),
                usuarioModel.getNome()
        );
        return ResponseEntity.ok().body(responseLoginDto);
    }

}
