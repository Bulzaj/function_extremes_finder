package com.sadsoft.functionextremesfinder.service.population_initializer;

import com.sadsoft.functionextremesfinder.model.GeneticAlgorithmPropertiesRequestDTO;
import com.sadsoft.functionextremesfinder.model.Individual;
import com.sadsoft.functionextremesfinder.model.Population;
import com.sadsoft.functionextremesfinder.until.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Component
public class PopulationInitializerImpl implements PopulationInitializer {

    private static final Logger log = LoggerFactory.getLogger(PopulationInitializerImpl.class);

    @Override
    public void initialize(Population population, GeneticAlgorithmPropertiesRequestDTO requestDTO) {

        log.debug("[Initializer] Initializer starts...");

        int geneLength = Util.getRangeLength(requestDTO.getMaxRange());
        int populationSize = requestDTO.getPopulationSize();
        Individual[] newPopulation = new Individual[populationSize];
        int[] newGenes;

        for (int i=0; i<populationSize; i++) {
            newGenes = new int[geneLength];
            newPopulation[i] = new Individual();
            for (int j=0; j<geneLength; j++) {
                newGenes[j] = Util.generateRandom(0, 1);
            }
            newPopulation[i].setGenes(newGenes);
        }
        population.setPopulation(newPopulation);
        Util.updatePopulation(population, requestDTO);
        log.debug("[Initializer] Initialize stops...");
    }


}
