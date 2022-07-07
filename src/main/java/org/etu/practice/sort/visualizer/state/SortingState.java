package org.etu.practice.sort.visualizer.state;

import java.util.Arrays;

public record SortingState<T extends Comparable<T>>(T[] sortingArray, int[] changedElementIndices) {

    public SortingState (T[] sortingArray, int[] changedElementIndices) {
        this.sortingArray = Arrays.copyOf(sortingArray, sortingArray.length);
        this.changedElementIndices = Arrays.copyOf(changedElementIndices, changedElementIndices.length);
    }

}
