package com.fightfit.fightfitapi.controller;

import com.fightfit.fightfitapi.dto.usuario.CreateUsuarioDto;
import com.fightfit.fightfitapi.dto.usuario.ResponseUsuarioDto;
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
                createUsuarioDto.senha()
        );
        return ResponseEntity.status(HttpStatus.OK).body(responseUsuarioDto);
    }
}
