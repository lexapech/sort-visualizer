package org.etu.practice.sort.visualizer.algorithm;

import org.etu.practice.sort.visualizer.state.SortingState;
import org.junit.jupiter.api.*;

import java.util.Arrays;

public class BitonicSortingTest {

    private BitonicSorting<Integer> integerBitonicSorting;

    @BeforeEach
    void prepare() {
        integerBitonicSorting = new BitonicSorting<>();
    }

    @Test
    public void sortArray() {
        Integer[] sortArray = {1,3,4,6,2,7,5,8};
        Integer[] answerArray = {1,2,3,4,5,6,7,8};
        SortingState<Integer> initState
                = new SortingState<>(sortArray, new int[0]);
        integerBitonicSorting.sort(initState);
        Integer[] resultArray = initState.sortingArray();
        Assertions.assertArrayEquals(answerArray,resultArray);
    }

    @Test
    public void incorrectSort() {
        Integer[] sortArray = {5,2,3,1,6,4};
        Integer[] answerArray = {1,2,3,5,6,4};
        SortingState<Integer> initState
                = new SortingState<>(sortArray, new int[0]);
        integerBitonicSorting.sort(initState);
        Integer[] resultArray = initState.sortingArray();
        Assertions.assertArrayEquals(answerArray,resultArray);
    }
}
