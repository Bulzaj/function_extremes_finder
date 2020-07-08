package com.sadsoft.functionextremesfinder.api;

import com.sadsoft.functionextremesfinder.model.GeneticAlgorithmPropertiesRequestDTO;
import com.sadsoft.functionextremesfinder.model.GeneticAlgorithmResponseDTO;
import com.sadsoft.functionextremesfinder.service.GeneticAlgorithmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GeneticAlgorithmController {

    private static final Logger log = LoggerFactory.getLogger(GeneticAlgorithmController.class);
    private final GeneticAlgorithmService service;

    public GeneticAlgorithmController(GeneticAlgorithmService service) {
        this.service = service;
    }

    @PostMapping("/run")
    public ResponseEntity<GeneticAlgorithmResponseDTO> run(@RequestBody GeneticAlgorithmPropertiesRequestDTO request) {
        return ResponseEntity.ok(service.run(request));
    };
}
