package com.fightfit.fightfitapi.service;

import com.fightfit.fightfitapi.dto.usuario.CreateUsuarioDto;
import com.fightfit.fightfitapi.model.UsuarioModel;
import com.fightfit.fightfitapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;



    public UsuarioModel salvarUsuario(CreateUsuarioDto createUsuarioDto){

        UsuarioModel usuarioModel = UsuarioModel.builder()
                .nome(createUsuarioDto.nome())
                .senha(createUsuarioDto.senha())
                .build();
        return usuarioRepository.save(usuarioModel);
    }
}
