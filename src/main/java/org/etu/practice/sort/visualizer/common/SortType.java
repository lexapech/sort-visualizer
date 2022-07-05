package org.etu.practice.sort.visualizer.common;

public enum SortType {

    BUBBLE_SORT {
        public String getLabel() {
            return "Сортировка пузырьком";
        }
    },
    QUICKSORT {
        public String getLabel() {
            return "Быстрая сортировка";
        }
    },
    BITONIC_SORT {
        public String getLabel() {
            return "Битонная сортировка";
        }
    };
    public abstract String getLabel();
    }
