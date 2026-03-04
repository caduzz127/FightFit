package com.fightfit.fightfitapi.service;

import com.fightfit.fightfitapi.dto.usuario.CreateUsuarioDto;
import com.fightfit.fightfitapi.dto.usuario.ResponseAllUsuario;
import com.fightfit.fightfitapi.dto.usuario.UpdateUsuarioDto;
import com.fightfit.fightfitapi.model.UsuarioModel;
import com.fightfit.fightfitapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;



    public UsuarioModel salvarUsuario(CreateUsuarioDto createUsuarioDto){

        if(usuarioRepository.findByName(createUsuarioDto.nome()).isPresent()){
           throw new RuntimeException("Nome de Usuário já cadastrado no sistema");
        }
        UsuarioModel usuarioModel = UsuarioModel.builder()
                .nome(createUsuarioDto.nome())
                .senha(createUsuarioDto.senha())
                .build();
        return usuarioRepository.save(usuarioModel);
    }

    public void deletarUsuario(UUID id){
        if(!usuarioRepository.findById(id).isPresent()){
            throw new RuntimeException("usuario não existe no sistema");
        }
        usuarioRepository.deleteById(id);
    }



    public UsuarioModel atualizarUsuario(UpdateUsuarioDto updateUsuarioDto){
        if(!usuarioRepository.findById(updateUsuarioDto.id()).isPresent()){
            throw new RuntimeException("usuario não existe no sistema");
        }
        UsuarioModel usuarioModelAtualizado = UsuarioModel.builder()
                .id(updateUsuarioDto.id())
                .nome(updateUsuarioDto.nome())
                .senha(updateUsuarioDto.senha())
                .build();

        return usuarioRepository.save(usuarioModelAtualizado);
    }


    public UsuarioModel findByNome(String nome){
        UsuarioModel usuarioModel = usuarioRepository.findByName(nome).orElseThrow( ()-> new RuntimeException("usuario nao encontrado"));
        return usuarioModel;
    }



//    public UsuarioModel findByNameAll(ResponseAllUsuario responseAllUsuario){
//        if(usuarioRepository.findByName(responseAllUsuario.nome()).isPresent()){
//            throw new RuntimeException("Nome de Usuário já cadastrado no sistema");
//        }
//        UsuarioModel usuarioModel =
//
//        return
//    }
}
