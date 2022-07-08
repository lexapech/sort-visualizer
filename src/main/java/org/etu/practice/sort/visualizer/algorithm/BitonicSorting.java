package org.etu.practice.sort.visualizer.algorithm;

import org.etu.practice.sort.visualizer.state.SortingState;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;


public class BitonicSorting<T extends Comparable<T>> extends SortingAlgorithmAbstract<T> {
    private final LinkedHashMap<Integer, SortingState<T>> result = new LinkedHashMap<>();
    private int index;
    private T duplicateElement;
    @Override
    @SuppressWarnings("unchecked")
    protected LinkedHashMap<Integer, SortingState<T>> startSortAlgorithm(SortingState<T> initialState) {
        T[] powerOfTwoArray;
        duplicateElement = initialState.sortingArray()[initialState.sortingArray().length-1];
        powerOfTwoArray = (T[])Array.newInstance(initialState.sortingArray()[0].getClass(),powerTwo(initialState.sortingArray().length));
        for(int i = 0; i < powerOfTwoArray.length; i++) {
            if (i < initialState.sortingArray().length) {
                powerOfTwoArray[i] = initialState.sortingArray()[i];
            }
            else {
                powerOfTwoArray[i] = duplicateElement;
            }
        }
        result.put(1, new SortingState<>(initialState.sortingArray(), new int[0]));
        index = 1;
        bitonicSort(powerOfTwoArray, 0, powerOfTwoArray.length, 1);
        return result;
    }

    @SuppressWarnings("unchecked")
    private void compAndSwap(T[] a, int i, int j, int dir) {

        if (((a[i].compareTo(a[j]) > 0) && dir == 1) ||
                ((a[i].compareTo(a[j]) < 0) && dir == 0)) {
            T temp = a[i];
            a[i] = a[j];
            a[j] = temp;

            ArrayList<T> tempArrayList = new ArrayList<>();

            for(T e : a) {
                if(e == duplicateElement && tempArrayList.contains(e)) continue;
                tempArrayList.add(e);
            }
            T[] tempArray = (T[])Array.newInstance(a[0].getClass(),tempArrayList.size());
            tempArray = tempArrayList.toArray(tempArray);
            result.put(++index,new SortingState<>(tempArray,new int[]{i,j}));
        }
    }

    private void bitonicMerge(T[] a, int low, int cnt, int dir) {
        if (cnt>1) {
            int k = cnt/2;
            for (int i=low; i<low+k; i++)
                compAndSwap(a,i, i+k, dir);
            bitonicMerge(a,low, k, dir);
            bitonicMerge(a,low+k, k, dir);
        }
    }

    private void bitonicSort(T[] a, int low, int cnt, int dir) {
        if (cnt>1) {
            int k = cnt/2;
            bitonicSort(a, low, k, 1);
            bitonicSort(a,low+k, k, 0);
            bitonicMerge(a, low, cnt, dir);
        }
    }

    private int powerTwo(int x) {
        if (x <= 0){
            return 1;
        } else if ((x & (x - 1)) == 0) {
            return x;
        } else {
            int n = (int)(Math.log(x) / Math.log(2)) + 1;
            return (int)Math.pow(2, n);
        }
    }


}
