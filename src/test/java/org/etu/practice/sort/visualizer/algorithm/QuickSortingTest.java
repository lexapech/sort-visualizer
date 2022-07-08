package org.etu.practice.sort.visualizer.algorithm;

import org.etu.practice.sort.visualizer.exception.SortVisualizerException;
import org.etu.practice.sort.visualizer.state.SortingState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class QuickSortingTest {

    private QuickSorting<Integer> integerQuickSorting;

    @BeforeEach
    void prepare() {
        integerQuickSorting = new QuickSorting<>();
    }

    @Test
    public void sortArray() throws SortVisualizerException {
        Integer[] sortArray = {5,8,2,7,3,1,6,9,4};
        Integer[] answerArray = {1,2,3,4,5,6,7,8,9};
        SortingState<Integer> initState
                = new SortingState<>(sortArray, new int[0]);
        integerQuickSorting.sort(initState);
        Integer[] resultArray = integerQuickSorting.goToLastStep().sortingArray();
        Assertions.assertArrayEquals(answerArray,resultArray);
    }

    @Test
    void emptyArray() throws SortVisualizerException {
        Integer[] sortArray = {};
        Integer[] answerArray = {};
        SortingState<Integer> initState
                = new SortingState<>(sortArray, new int[0]);
        integerQuickSorting.sort(initState);
        Integer[] resultArray = integerQuickSorting.goToLastStep().sortingArray();
        Assertions.assertArrayEquals(answerArray,resultArray);
    }
}
