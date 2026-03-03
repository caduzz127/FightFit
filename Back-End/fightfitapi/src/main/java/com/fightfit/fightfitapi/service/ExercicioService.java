package com.fightfit.fightfitapi.service;

import com.fightfit.fightfitapi.repository.ExercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExercicioService {
    @Autowired
    ExercicioRepository exercicioRepository;


}
