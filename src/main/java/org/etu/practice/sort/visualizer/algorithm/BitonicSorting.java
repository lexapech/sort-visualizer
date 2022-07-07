package org.etu.practice.sort.visualizer.algorithm;

import org.etu.practice.sort.visualizer.state.SortingState;
import java.util.LinkedHashMap;


public class BitonicSorting<T extends Comparable<T>> extends SortingAlgorithmAbstract<T> {
    private final LinkedHashMap<Integer, SortingState<T>> result = new LinkedHashMap<>();
    private int index;

    @Override
    protected LinkedHashMap<Integer, SortingState<T>> startSortAlgorithm(SortingState<T> initialState) {
        result.put(1, new SortingState<>(initialState.sortingArray(), new int[0]));
        index = 1;
        bitonicSort(initialState.sortingArray(), 0, initialState.sortingArray().length, 1);
        return result;
    }


    void compAndSwap(T[] a, int i, int j, int dir) {

        if (((a[i].compareTo(a[j]) > 0) && dir == 1) ||
                ((a[i].compareTo(a[j]) < 0) && dir == 0)) {
            T temp = a[i];
            a[i] = a[j];
            a[j] = temp;

            result.put(++index,new SortingState<>(a,new int[]{i,j}));
        }
    }

    void bitonicMerge(T[] a, int low, int cnt, int dir) {
        if (cnt>1) {
            int k = cnt/2;
            for (int i=low; i<low+k; i++)
                compAndSwap(a,i, i+k, dir);
            bitonicMerge(a,low, k, dir);
            bitonicMerge(a,low+k, k, dir);
        }
    }

    void bitonicSort(T[] a, int low, int cnt, int dir) {
        if (cnt>1) {
            int k = cnt/2;
            bitonicSort(a, low, k, 1);
            bitonicSort(a,low+k, k, 0);
            bitonicMerge(a, low, cnt, dir);
        }
    }

}
