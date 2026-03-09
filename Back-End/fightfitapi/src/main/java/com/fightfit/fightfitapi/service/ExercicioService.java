package com.fightfit.fightfitapi.service;

import com.fightfit.fightfitapi.dto.exercicio.CreateExercicioDto;
import com.fightfit.fightfitapi.dto.exercicio.DeleteExercicioDto;
import com.fightfit.fightfitapi.dto.exercicio.UpdateExercicioDto;
import com.fightfit.fightfitapi.dto.treino.CreateTreinoDto;
import com.fightfit.fightfitapi.model.ExercicioModel;
import com.fightfit.fightfitapi.model.TreinoModel;
import com.fightfit.fightfitapi.repository.ExercicioRepository;
import com.fightfit.fightfitapi.repository.TreinoRepository;
import jakarta.transaction.Transactional;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ExercicioService {
    @Autowired
    ExercicioRepository exercicioRepository;

    @Autowired
    TreinoRepository treinoRepository;

    public ExercicioModel salvarExercicio(CreateExercicioDto exercicioDto) {
        TreinoModel treinoModel = treinoRepository.findById(exercicioDto.idTreino()).orElseThrow(()-> new RuntimeException("Treino não encontrado"));
        ExercicioModel exercicioModel = ExercicioModel.builder()
                .nome(exercicioDto.nome())
                .serie(exercicioDto.serie())
                .repeticoes(exercicioDto.repeticoes())
                .carga(exercicioDto.carga())
                .treinos(treinoModel)
                .build();

        return exercicioRepository.save(exercicioModel);
    }




    public ExercicioModel atualizarExercicio(UpdateExercicioDto UpdateExercicioDto) {
        ExercicioModel exercicioModel = exercicioRepository.findById(UpdateExercicioDto.id()).orElseThrow(()-> new RuntimeException("exercicio não foi encontrado"));

        exercicioModel.setCarga(UpdateExercicioDto.carga());
        exercicioModel.setSerie(UpdateExercicioDto.serie());
        exercicioModel.setNome(UpdateExercicioDto.nome());
        exercicioModel.setRepeticoes(UpdateExercicioDto.repeticoes());

        return exercicioRepository.save(exercicioModel);
    }


    public void deleteExercicio(UUID id) {
       if(!exercicioRepository.findById(id).isPresent()){
           throw new RuntimeException("Exercicio não foi encontrado");
       }
         exercicioRepository.deleteById(id);
    }

    public List<ExercicioModel> buscarExercicioPorNome(String nome){
        System.out.println("nome: "+nome);
        return exercicioRepository.findByNome("%"+nome+"%");
    }
}
