package com.fightfit.fightfitapi.controller;

import com.fightfit.fightfitapi.dto.grupo.ResponseGrupoDto;
import com.fightfit.fightfitapi.dto.ranking.CreateRankingDto;
import com.fightfit.fightfitapi.dto.ranking.ResponseRankingDto;
import com.fightfit.fightfitapi.dto.ranking.ResponseUsuarioInRankingDto;
import com.fightfit.fightfitapi.dto.usuario.ResponseLoginDto;
import com.fightfit.fightfitapi.dto.usuario.ResponseUsuarioDto;
import com.fightfit.fightfitapi.model.RankingModel;
import com.fightfit.fightfitapi.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fightfit/ranking")
public class RankingController {

    @Autowired
    private RankingService rankingService;

    @PostMapping("/cadastrar")
    public ResponseEntity<ResponseRankingDto> create(@RequestBody CreateRankingDto createRankingDto) {
        RankingModel rankingModel = rankingService.createRanking(createRankingDto);
        ResponseGrupoDto responseGrupoDto =  new ResponseGrupoDto(rankingModel.getGrupo().getId(),rankingModel.getGrupo().getNome());
        List<ResponseUsuarioInRankingDto> responseUsuariosDto =  rankingModel.getUsuarios().stream().map(usuarios -> new ResponseUsuarioInRankingDto(
                usuarios.getUsuario().getId(),
                usuarios.getUsuario().getNome(),
                usuarios.getCarga()
        )).toList();

        ResponseRankingDto responseRankingDto = new ResponseRankingDto(
                rankingModel.getId(),
                rankingModel.getNomeDoRanking(),
                responseGrupoDto,
                responseUsuariosDto

        );
        return ResponseEntity.status(HttpStatus.CREATED).body(responseRankingDto);
    }
}
