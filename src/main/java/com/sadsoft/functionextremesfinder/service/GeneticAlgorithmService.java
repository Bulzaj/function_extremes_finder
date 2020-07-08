package com.sadsoft.functionextremesfinder.service;

import com.sadsoft.functionextremesfinder.model.GeneticAlgorithmResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface GeneticAlgorithmService {

    ResponseEntity<GeneticAlgorithmResponseDTO> run(String functionBody) ;
}
