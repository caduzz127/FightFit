package com.fightfit.fightfitapi.service;

import com.fightfit.fightfitapi.dto.grupo.CreateGrupoDto;
import com.fightfit.fightfitapi.dto.grupo.JoinGrupoDto;
import com.fightfit.fightfitapi.dto.grupo.UsuarioGrupoDto;
import com.fightfit.fightfitapi.model.GrupoModel;
import com.fightfit.fightfitapi.model.GrupoUsuariosModel;
import com.fightfit.fightfitapi.model.UsuarioModel;
import com.fightfit.fightfitapi.repository.GrupoRepository;
import com.fightfit.fightfitapi.repository.GrupoUsuariosRepository;
import com.fightfit.fightfitapi.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GrupoUsuariosRepository grupoUsuariosRepository;

    public GrupoModel criarGrupo(CreateGrupoDto createGrupoDto){
        if(grupoRepository.findByName(createGrupoDto.nome()).isPresent()){
            throw new RuntimeException("Grupo já existente");
        }else{
            UsuarioModel usuario = usuarioRepository.findById(createGrupoDto.idUsuario()).orElseThrow(()-> new RuntimeException("Não foi possível encontrar o usuário"));

            GrupoModel grupo = GrupoModel.builder()
                    .nome(createGrupoDto.nome())
                    .senha(createGrupoDto.senha())
                    .build();

            GrupoModel grupoCriado = grupoRepository.save(grupo);

            GrupoUsuariosModel grupoUsuariosModel = GrupoUsuariosModel.builder()
                    .grupo(grupoCriado)
                    .usuario(usuario)
                    .cargo("Administrador")
                    .build();
            grupoUsuariosRepository.save(grupoUsuariosModel);

            grupoCriado.setUsuarios(grupoUsuariosRepository.findByIdGrupo(grupoCriado.getId()));

            return grupoCriado;

        }

    }

    public GrupoModel entrarGrupo(JoinGrupoDto joinGrupoDto){
        if(!grupoRepository.findByName(joinGrupoDto.nomeGrupo()).isPresent()){
            throw new RuntimeException("Grupo não foi encontrado");
        }else{
            GrupoModel grupoModel = grupoRepository.findByNameAndSenha(joinGrupoDto.nomeGrupo(), joinGrupoDto.senhaGrupo()).orElseThrow(() -> new RuntimeException("Nome ou senha Errados"));
            UsuarioModel usuarioModel = usuarioRepository.findById(joinGrupoDto.id_usuario()).orElseThrow(()-> new RuntimeException("Usuario encontrado"));

            GrupoUsuariosModel grupoUsuariosModel = GrupoUsuariosModel.builder()
                    .grupo(grupoModel)
                    .usuario(usuarioModel)
                    .cargo("user")
                    .build();

            grupoUsuariosRepository.save(grupoUsuariosModel);

            grupoModel.setUsuarios(grupoUsuariosRepository.findByIdGrupo(grupoModel.getId()));

            return grupoModel;
        }

    }


//    public void excluirGrupoPorNome(String nome){
//        if(!grupoRepository.findByName(nome).isPresent()){
//            throw new RuntimeException("Grupo Não Existente");
//        }
//        grupoRepository.deleteByName(nome);
//    }
}
