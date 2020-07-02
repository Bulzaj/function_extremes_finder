package com.sadsoft.functionextremesfinder.population.individual;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class IndividualImplTest {

    private static final Logger log = LoggerFactory.getLogger(IndividualImplTest.class);

    @Test
    public void getFitness_ShouldReturnRightDecimalValue() {
        //Given
        int[] genes = new int[] {1,1,0,1,0};
        double expected = 26;
        Individual individual = new IndividualImpl(genes);
        log.debug("Evaluated genes: {}", individual.getGenes());
        assertEquals(expected, individual.getFitness());
    }

    @Test
    public void constructor_WhenArgumentIsNull_ThenThrowException() {
        assertThrows(NullPointerException.class, () -> new IndividualImpl(null));
    }

    @Test
    public void constructor_WhenWrongArguments_ThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new IndividualImpl(new int[]{1,1,2,0}));
    }
}