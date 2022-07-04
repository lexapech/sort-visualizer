package org.etu.practice.sort.visualizer.algorithm;

import org.etu.practice.sort.visualizer.common.SortEnum;

import java.util.Arrays;

public record SortingState(SortEnum sortingType, int[] sortingArray, int[] changedElementIndices) {

    public SortingState (SortEnum sortingType, int[] sortingArray, int[] changedElementIndices) {
        this.sortingType = sortingType;
        this.sortingArray = Arrays.copyOf(sortingArray, sortingArray.length);
        this.changedElementIndices = Arrays.copyOf(changedElementIndices, changedElementIndices.length);
    }

}
