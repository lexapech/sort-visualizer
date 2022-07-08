package org.etu.practice.sort.visualizer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

public class SortVisualizerTest {



    @Test
    public void automaticGenerateArray() {
        int arraySize = 5;
        Integer[] array = SortVisualizer.GenerateArray.generateArray(arraySize);
        Assertions.assertEquals(arraySize , array.length);
    }
}
