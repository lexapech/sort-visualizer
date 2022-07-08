package org.etu.practice.sort.visualizer.algorithm;

import org.etu.practice.sort.visualizer.exception.SortVisualizerException;
import org.etu.practice.sort.visualizer.state.SortingState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class SortingAlgorithmTest {

    private final Integer[] integerArray = {3,2,6,1,5,4,8,7};

    private SortingAlgorithm<Integer> integerSortingAlgorithm;
    private SortingState<Integer> integerSortingState;

    @BeforeEach
    void prepare() {
        integerSortingAlgorithm = null;
        integerSortingState = new SortingState<>(integerArray, new int[0]);
    }

    @Test
    public void nextStepExceptionQuickSort() throws SortVisualizerException {
        integerSortingAlgorithm = new QuickSorting<>();
        integerSortingAlgorithm.sort(integerSortingState);
        var step = integerSortingAlgorithm.goToLastStep();
        Assertions.assertThrows(SortVisualizerException.class, ()-> {
            integerSortingAlgorithm.nextStep();
        });
    }

    @Test
    public void previousStepExceptionQuickSort() throws SortVisualizerException {
        integerSortingAlgorithm = new QuickSorting<>();
        integerSortingAlgorithm.sort(integerSortingState);
        Assertions.assertThrows(SortVisualizerException.class, ()-> {
            integerSortingAlgorithm.previousStep();
        });
    }

    @Test
    public void firstStepExceptionQuickSort() throws SortVisualizerException {
        integerSortingAlgorithm = new QuickSorting<>();
        Assertions.assertThrows(SortVisualizerException.class, ()-> {
            integerSortingAlgorithm.goToFirstStep();
        });
    }

    @Test
    public void lastStepExceptionQuickSort() throws SortVisualizerException {
        integerSortingAlgorithm = new QuickSorting<>();
        Assertions.assertThrows(SortVisualizerException.class, ()-> {
            integerSortingAlgorithm.goToLastStep();
        });
    }

    @Test
    public void nextStepExceptionBubbleSort() throws SortVisualizerException {
        integerSortingAlgorithm = new BubbleSorting<>();
        integerSortingAlgorithm.sort(integerSortingState);
        var step = integerSortingAlgorithm.goToLastStep();
        Assertions.assertThrows(SortVisualizerException.class, ()-> {
            integerSortingAlgorithm.nextStep();
        });
    }

    @Test
    public void previousStepExceptionBubbleSort() throws SortVisualizerException {
        integerSortingAlgorithm = new BubbleSorting<>();
        integerSortingAlgorithm.sort(integerSortingState);
        Assertions.assertThrows(SortVisualizerException.class, ()-> {
            integerSortingAlgorithm.previousStep();
        });
    }

    @Test
    public void firstStepExceptionBubbleSort() throws SortVisualizerException {
        integerSortingAlgorithm = new BubbleSorting<>();
        Assertions.assertThrows(SortVisualizerException.class, ()-> {
            integerSortingAlgorithm.goToFirstStep();
        });
    }

    @Test
    public void lastStepExceptionBubbleSort() throws SortVisualizerException {
        integerSortingAlgorithm = new BubbleSorting<>();
        Assertions.assertThrows(SortVisualizerException.class, ()-> {
            integerSortingAlgorithm.goToLastStep();
        });
    }

    @Test
    public void nextStepExceptionBitonicSort() throws SortVisualizerException {
        integerSortingAlgorithm = new BitonicSorting<>();
        integerSortingAlgorithm.sort(integerSortingState);
        var step = integerSortingAlgorithm.goToLastStep();
        Assertions.assertThrows(SortVisualizerException.class, ()-> {
            integerSortingAlgorithm.nextStep();
        });
    }

    @Test
    public void previousStepExceptionBitonicSort() throws SortVisualizerException {
        integerSortingAlgorithm = new BitonicSorting<>();
        integerSortingAlgorithm.sort(integerSortingState);
        Assertions.assertThrows(SortVisualizerException.class, ()-> {
            integerSortingAlgorithm.previousStep();
        });
    }

    @Test
    public void firstStepExceptionBitonicSort() throws SortVisualizerException {
        integerSortingAlgorithm = new BitonicSorting<>();
        Assertions.assertThrows(SortVisualizerException.class, ()-> {
            integerSortingAlgorithm.goToFirstStep();
        });
    }

    @Test
    public void lastStepExceptionBitonicSort() throws SortVisualizerException {
        integerSortingAlgorithm = new BitonicSorting<>();
        Assertions.assertThrows(SortVisualizerException.class, ()-> {
            integerSortingAlgorithm.goToLastStep();
        });
    }
}
