package org.etu.practice.sort.visualizer.algorithm;

import org.etu.practice.sort.visualizer.state.SortingState;
import java.util.LinkedHashMap;

public class BubbleSorting<T extends Comparable<T>> extends SortingAlgorithmAbstract<T> {
    /*private void swap(T[] arr, int i, int j) {
        T buf;
        buf = arr[i];
        arr[i] = arr[j];
        arr[j] = buf;
    }*/
    @Override
    protected LinkedHashMap<Integer, SortingState<T>> startSortAlgorithm(SortingState<T> initialState) {

        LinkedHashMap<Integer, SortingState<T>> result = new LinkedHashMap<>();

        T[] arr = initialState.sortingArray();
        int count = 1;
        result.put(count, new SortingState<>(initialState.sortingArray(), new int[0]));
        boolean notSorted = true;
        //T buf;
        while (notSorted) {
            notSorted = false;
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i].compareTo(arr[i+1]) > 0) {
                    notSorted = true;

                    //замена i-го и i+1-го элементов
                    /*buf = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = buf;*/
                    swap(arr, i, i+1);
                    // запись состояния
                    int[] indexesOfChangingElements = {i, i+1};
                    SortingState<T> stateAfterChange = new SortingState<>(arr,indexesOfChangingElements);

                    result.put(++count,stateAfterChange);
                }
            }
        }

       /* for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 1; j < arr.length - i; j++) {
                if ( arr[i].compareTo(arr[j]) < 0) {

                }
            }
        }*/
        // insert algorithm implementation
        // put
        // put


        return result;
    }



}
