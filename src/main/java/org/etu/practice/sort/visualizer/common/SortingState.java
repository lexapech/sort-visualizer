package org.etu.practice.sort.visualizer.common;

import org.etu.practice.sort.visualizer.common.SortType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record SortingState<T extends Comparable<T>>(SortType sortingType, List<T> sortingArray, int[] changedElementIndices) {

    public SortingState (SortType sortingType, List<T> sortingArray, int[] changedElementIndices) {
        this.sortingType = sortingType;
        this.sortingArray = new ArrayList<>(sortingArray);
        this.changedElementIndices = Arrays.copyOf(changedElementIndices, changedElementIndices.length);
    }

}
