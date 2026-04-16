package com.fightfit.fightfitapi.service;

import com.fightfit.fightfitapi.dto.ranking.CreateRankingDto;
import com.fightfit.fightfitapi.model.*;
import com.fightfit.fightfitapi.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RankingService {

    @Autowired
    private RankingRepository rankingRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GrupoUsuariosRepository  grupoUsuariosRepository;

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private RankingUsuarioRepository rankingUsuarioRepository;

    public RankingModel createRanking(CreateRankingDto CreateRanking) {
        if(!usuarioRepository.findById(CreateRanking.idUsuario()).isPresent() || !grupoRepository.findById(CreateRanking.idGrupo()).isPresent()) {
            throw new RuntimeException("Grupo ou Usuario não encotrado");
        }else{
            GrupoUsuariosModel grupoUsuariosModel= grupoUsuariosRepository.findByUsuario(CreateRanking.idUsuario()).orElseThrow(()-> new RuntimeException("Usuario nao encontrado"));
            UsuarioModel usuarioModel =  usuarioRepository.findById(CreateRanking.idUsuario()).orElseThrow(()-> new RuntimeException("Usuario nao encontrado"));
            GrupoModel grupoModel = grupoRepository.findById(CreateRanking.idGrupo()).orElseThrow(()-> new RuntimeException("Grupo nao encontrado"));

            if (!grupoUsuariosModel.getCargo().equals("Administrador")) {
                throw new RuntimeException("O Usuario não é um administrador para poder criar um grupo");
            }else{


                RankingModel rankingModel = RankingModel.builder()
                        .nomeDoRanking(CreateRanking.nome())
                        .grupo(grupoModel)
                        .build();


                RankingModel rankingCriado = rankingRepository.save(rankingModel);

                RankingUsuarioModel rankingUsuarioModel = RankingUsuarioModel.builder()
                        .usuario(usuarioModel)
                        .carga(CreateRanking.carga())
                        .ranking(rankingCriado)
                        .build();

                rankingUsuarioRepository.save(rankingUsuarioModel);
                List<RankingUsuarioModel> newRankingUsuarioModel =  rankingUsuarioRepository.findAllByRanking(rankingCriado.getId());
               rankingCriado.setUsuarios(newRankingUsuarioModel);
                return rankingCriado;
            }

        }

    }
}
