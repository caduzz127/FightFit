package com.fightfit.fightfitapi.service;

import com.fightfit.fightfitapi.dto.treino.CreateTreinoDto;

import com.fightfit.fightfitapi.dto.treino.UpdateTreinoDto;
import com.fightfit.fightfitapi.model.TreinoModel;
import com.fightfit.fightfitapi.model.UsuarioModel;
import com.fightfit.fightfitapi.repository.TreinoRepository;
import com.fightfit.fightfitapi.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Transactional
@Service
public class TreinoService {

    @Autowired
    TreinoRepository treinoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;



    public TreinoModel salvarTreino(CreateTreinoDto createTreinoDto){
        UsuarioModel usuarioModel = usuarioRepository.findById(createTreinoDto.idUsuario()).orElseThrow( ()-> new RuntimeException("usuario nao encontrado"));
        TreinoModel treinoModel =  TreinoModel.builder()
                .nome(createTreinoDto.nome())
                .usuario(usuarioModel)
                .build();
        return treinoRepository.save(treinoModel);
    }


    public TreinoModel atualizarTreino(UpdateTreinoDto updateTreinoDto){
        TreinoModel treinoModel = treinoRepository.findById(updateTreinoDto.id()).orElseThrow(() -> new RuntimeException("Id Invalido"));
        TreinoModel treinoModelAtualizado = new TreinoModel();
        treinoModelAtualizado.setId(updateTreinoDto.id());
        treinoModelAtualizado.setNome(updateTreinoDto.nome());
        treinoModelAtualizado.setUsuario(treinoModel.getUsuario());
        return treinoRepository.save(treinoModelAtualizado);
    }


    public void deletarTreino(UUID uuid){
        if (!treinoRepository.findById(uuid).isPresent()){
            throw new RuntimeException("Não existe nenhum treino com esse id");
        }
        treinoRepository.deleteById(uuid);
    }

    public List<TreinoModel> buscarTreinoPorIdUsuario(UUID uuid){

        if(!usuarioRepository.findById(uuid).isPresent()){
            throw new RuntimeException("Não existe nenhum usuario com esse id");
        }

        return treinoRepository.findTreinoByIdUsuario(uuid);
    }



    public List<TreinoModel> buscarTreinoPorNome(String nome){


        return treinoRepository.findTreinoByName("%"+nome+"%");
    }




}
