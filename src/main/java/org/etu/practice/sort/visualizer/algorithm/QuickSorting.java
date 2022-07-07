package org.etu.practice.sort.visualizer.algorithm;

import org.etu.practice.sort.visualizer.state.SortingState;
import java.util.LinkedHashMap;

public class QuickSorting<T extends Comparable<T>> extends SortingAlgorithmAbstract<T> {
    private final LinkedHashMap<Integer, SortingState<T>> result = new LinkedHashMap<>();
    private int count = 1;
    private void quickSort(T[] source, int leftBorder, int rightBorder) {
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        T pivot = source[(leftMarker + rightMarker) / 2];
        do {
            // Двигаем левый маркер слева направо пока элемент меньше, чем pivot
            while (source[leftMarker].compareTo(pivot) < 0) {
                leftMarker++;
            }
            // Двигаем правый маркер, пока элемент больше, чем pivot
            while (source[rightMarker].compareTo(pivot) > 0) {
                rightMarker--;
            }
            // Проверим, не нужно обменять местами элементы, на которые указывают маркеры
            if (leftMarker <= rightMarker) {
                // Левый маркер будет меньше правого только если мы должны выполнить swap
                if (leftMarker < rightMarker) {
                    swap(source, leftMarker, rightMarker);

                    int[] indexesOfChangingElements = {leftMarker, rightMarker};
                    SortingState<T> stateAfterChange = new SortingState<>(source,indexesOfChangingElements);
                    result.put(++count,stateAfterChange);
                    /*T tmp = source[leftMarker];
                    source[leftMarker] = source[rightMarker];
                    source[rightMarker] = tmp;*/
                }
                // Сдвигаем маркеры, чтобы получить новые границы

                leftMarker++;
                rightMarker--;
            }
        } while (leftMarker <= rightMarker);

        // Выполняем рекурсивно для частей
        if (leftMarker < rightBorder) {
            quickSort(source, leftMarker, rightBorder);
        }
        if (leftBorder < rightMarker) {
            quickSort(source, leftBorder, rightMarker);
        }
    }
    @Override
    protected LinkedHashMap<Integer, SortingState<T>> startSortAlgorithm(SortingState<T> initialState) {
        //LinkedHashMap<Integer, SortingState<T>> result = new LinkedHashMap<>();
        count = 1;
        result.put(1, new SortingState<>(initialState.sortingArray(), new int[0]));
        T[] arr = initialState.sortingArray();
        quickSort(arr,0, arr.length - 1);
        // insert algorithm implementation
        // put
        // put

        return result;
    }

}
