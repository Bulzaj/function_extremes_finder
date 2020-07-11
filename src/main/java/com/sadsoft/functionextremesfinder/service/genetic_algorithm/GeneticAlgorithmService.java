package com.sadsoft.functionextremesfinder.service.genetic_algorithm;

import com.sadsoft.functionextremesfinder.model.GeneticAlgorithmPropertiesRequestDTO;
import com.sadsoft.functionextremesfinder.model.GeneticAlgorithmResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface GeneticAlgorithmService {

    GeneticAlgorithmResponseDTO run(GeneticAlgorithmPropertiesRequestDTO requestDTO) ;
}
