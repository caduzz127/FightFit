package com.fightfit.fightfitapi.service;

import com.fightfit.fightfitapi.dto.grupo.CreateGrupoDto;
import com.fightfit.fightfitapi.dto.grupo.JoinGrupoDto;
import com.fightfit.fightfitapi.model.GrupoModel;
import com.fightfit.fightfitapi.model.UsuarioModel;
import com.fightfit.fightfitapi.repository.GrupoRepository;
import com.fightfit.fightfitapi.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public GrupoModel salvarGrupo(CreateGrupoDto createGrupoDto){
        if(grupoRepository.findByName(createGrupoDto.nome()).isPresent()){
            throw new RuntimeException("Grupo já existente");
        }else{
            UsuarioModel usuario = usuarioRepository.findById(createGrupoDto.idUsuario()).orElseThrow(()-> new RuntimeException("ID errado"));
            GrupoModel grupo = GrupoModel.builder()
                    .nome(createGrupoDto.nome())
                    .usuario(usuario)
                    .cargo("Administrador")
                    .build();
            return grupoRepository.save(grupo);
        }

    }

    public GrupoModel entrarNoGrupo(JoinGrupoDto joinGrupoDto){
        if(!grupoRepository.findByName(joinGrupoDto.nome()).isPresent()){
            throw new RuntimeException("Grupo não foi encontrado");
        }else{

            return null;
        }

    }


    public void excluirGrupoPorNome(String nome){
        if(!grupoRepository.findByName(nome).isPresent()){
            throw new RuntimeException("Grupo Não Existente");
        }
        grupoRepository.deleteByName(nome);
    }
}
