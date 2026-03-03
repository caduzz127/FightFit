package com.fightfit.fightfitapi.controller;

import com.fightfit.fightfitapi.dto.usuario.CreateUsuarioDto;
import com.fightfit.fightfitapi.dto.usuario.DeleteUsuarioDto;
import com.fightfit.fightfitapi.dto.usuario.ResponseUsuarioDto;
import com.fightfit.fightfitapi.dto.usuario.UpdateUsuarioDto;
import com.fightfit.fightfitapi.model.UsuarioModel;
import com.fightfit.fightfitapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/fightfit/usuario")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<ResponseUsuarioDto>salvarUsuario(@RequestBody CreateUsuarioDto createUsuarioDto){
        UsuarioModel usuarioModel = usuarioService.salvarUsuario(createUsuarioDto);

        ResponseUsuarioDto responseUsuarioDto = new ResponseUsuarioDto(
                usuarioModel.getId(),
                usuarioModel.getNome(),
                usuarioModel.getSenha(),
                usuarioModel.getTreinos()
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
                usuarioModel.getSenha(),
                usuarioModel.getTreinos()
        );
        return ResponseEntity.ok().body(responseUsuarioDto);
    }


    @GetMapping("/buscarUsuarios")
    public ResponseEntity<ResponseUsuarioDto> buscarUsuarios(@RequestBody String nome){
        UsuarioModel usuarioModel = usuarioService.findByNome(nome);

        ResponseUsuarioDto responseUsuarioDto = new ResponseUsuarioDto(
                usuarioModel.getId(),
                usuarioModel.getNome(),
                usuarioModel.getSenha(),
                usuarioModel.getTreinos()
        );
        return ResponseEntity.ok().body(responseUsuarioDto);
    }
}
