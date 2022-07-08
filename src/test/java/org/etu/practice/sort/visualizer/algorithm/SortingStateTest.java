package org.etu.practice.sort.visualizer.algorithm;

import org.etu.practice.sort.visualizer.state.SortingState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortingStateTest {

    private SortingState<String> stringSortingState;
    private SortingState<Integer> intSortingState;
    private final Integer[] integerArray = {1, 3, 4, 0};
    private final int[] indices = new int[2];

    @BeforeEach
    void init() {
        indices[0] = 2;
        intSortingState = new SortingState<>(integerArray, indices);
    }

    @Test
    void sortingArray() {
        Integer[] result = intSortingState.sortingArray();
        assertNotEquals(integerArray, result);
        assertNotSame(integerArray, result);

        assertEquals(integerArray[0], result[0]);
        assertEquals(integerArray[1], result[1]);
        assertEquals(integerArray[2], result[2]);
        assertEquals(integerArray[3], result[3]);
    }

    @Test
    void changedElementIndices() {
        int[] result = intSortingState.changedElementIndices();
        assertNotEquals(indices, result);
        assertNotSame(indices, result);

        assertEquals(indices[0], result[0]);
        assertEquals(indices[1], result[1]);
    }

}