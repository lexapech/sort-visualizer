package org.etu.practice.sort.visualizer.algorithm;

import org.etu.practice.sort.visualizer.common.SortType;
import org.etu.practice.sort.visualizer.common.SortingState;

import java.util.*;

public class BitonicSorting<T extends Comparable<T>> extends SortingAlgorithmAbstract<T> {

    ArrayList<SortingState<T>> result;
    @Override
    protected LinkedHashMap<Integer, SortingState<T>> startSortAlgorithm(SortingState<T> initialState) {
        result = new ArrayList<>();
        bitonicSort(initialState.sortingArray(), 0, initialState.sortingArray().size(), 1);
        LinkedHashMap<Integer,SortingState<T>> resMap = new LinkedHashMap<>();
        for(int i = 0; i < result.size(); i++)
        {
            resMap.put(i,result.get(i));
        }
        return resMap;
    }


    void compAndSwap(List<T> a, int i, int j, int dir) {

        if (((a.get(i).compareTo(a.get(j)) > 0) && dir == 1) ||
                ((a.get(i).compareTo(a.get(j)) < 0) && dir == 0)) {
            T temp = a.get(i);
            a.set(i,a.get(j));
            a.set(j,temp);

            result.add(new SortingState<>(SortType.BITONIC_SORT,a,new int[]{i,j}));
        }
    }

    void bitonicMerge(List<T> a, int low, int cnt, int dir) {
        if (cnt>1) {
            int k = cnt/2;
            for (int i=low; i<low+k; i++)
                compAndSwap(a,i, i+k, dir);
            bitonicMerge(a,low, k, dir);
            bitonicMerge(a,low+k, k, dir);
        }
    }

    void bitonicSort(List<T> a, int low, int cnt, int dir) {
        if (cnt>1) {
            int k = cnt/2;
            bitonicSort(a, low, k, 1);
            bitonicSort(a,low+k, k, 0);
            bitonicMerge(a, low, cnt, dir);
        }
    }

}
