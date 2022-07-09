package org.etu.practice.sort.visualizer.algorithm;

import org.etu.practice.sort.visualizer.exception.SortVisualizerException;
import org.etu.practice.sort.visualizer.state.SortingState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class BubbleSortingTest {

    private BubbleSorting<Integer> integerBubbleSorting;

    @BeforeEach
    void prepare() {
        integerBubbleSorting = new BubbleSorting<>();
    }

    @Test
    public void sortArray() throws SortVisualizerException {
        Integer[] sortArray = {5,8,2,7,3,1,6,9,4};
        Integer[] answerArray = {1,2,3,4,5,6,7,8,9};
        SortingState<Integer> initState
                = new SortingState<>(sortArray, new int[0]);
        integerBubbleSorting.sort(initState);
        Integer[] resultArray = integerBubbleSorting.goToLastStep().sortingArray();
        Assertions.assertArrayEquals(answerArray,resultArray);
    }

    @Test
    void emptyArray() throws SortVisualizerException {
        Integer[] sortArray = {};
        Integer[] answerArray = {};
        SortingState<Integer> initState
                = new SortingState<>(sortArray, new int[0]);
        integerBubbleSorting.sort(initState);
        Integer[] resultArray = integerBubbleSorting.goToLastStep().sortingArray();
        Assertions.assertArrayEquals(answerArray,resultArray);
    }

    @Test
    void nullArray() {
        Integer[] sortArray = null;
        Assertions.assertThrows(NullPointerException.class, () -> {
            SortingState<Integer> initState
                    = new SortingState<>(sortArray, new int[0]);
        });
    }
}
