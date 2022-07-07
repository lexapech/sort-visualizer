package org.etu.practice.sort.visualizer.common;

import org.etu.practice.sort.visualizer.algorithm.BitonicSorting;
import org.etu.practice.sort.visualizer.algorithm.BubbleSorting;
import org.etu.practice.sort.visualizer.algorithm.QuickSorting;
import org.etu.practice.sort.visualizer.algorithm.SortingAlgorithm;

public enum SortType {
    BUBBLE_SORT("Сортировка пузырьком", new BubbleSorting<>()) {
        @Override
        public SortingAlgorithm<Integer> getIntegerSorting() {
            return new BubbleSorting<>();
        }
    },
    QUICKSORT("Быстрая сортировка", new QuickSorting<>()) {
        @Override
        public SortingAlgorithm<Integer> getIntegerSorting() {
            return new QuickSorting<>();
        }
    },
    BITONIC_SORT("Битонная сортировка", new BitonicSorting<>()) {
        @Override
        public SortingAlgorithm<Integer> getIntegerSorting() {
            return new BitonicSorting<>();
        }
    };

    private final String label;
    private final SortingAlgorithm<String> stringSorting;
    //private final SortingAlgorithm<Integer> integerSorting;

    SortType(String label,
             SortingAlgorithm<String> stringSortingAlgorithm
             /*SortingAlgorithm<Integer> integerSortingAlgorithm*/) {
        this.label = label;
        this.stringSorting = stringSortingAlgorithm;
        //this.integerSorting = integerSortingAlgorithm;
    }

    public String getLabel() {
        return this.label;
    }

    public SortingAlgorithm<String> getStringSorting() {
        return  this.stringSorting;
    }

    public abstract SortingAlgorithm<Integer> getIntegerSorting();

}
