package org.etu.practice.sort.visualizer.common;

public enum SortType {

    BUBBLE_SORT("Сортировка пузырьком"),
    QUICKSORT("Быстрая сортировка"),
    BITONIC_SORT("Битонная сортировка");

    private final String label;

    SortType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}