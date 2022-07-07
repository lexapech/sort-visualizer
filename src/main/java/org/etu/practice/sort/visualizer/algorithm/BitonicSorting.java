package org.etu.practice.sort.visualizer.algorithm;

import org.etu.practice.sort.visualizer.state.SortingState;
import java.util.LinkedHashMap;

public class BitonicSorting<T extends Comparable<T>> extends SortingAlgorithmAbstract<T> {

    @Override
    protected LinkedHashMap<Integer, SortingState<T>> startSortAlgorithm(SortingState<T> initialState) {
        LinkedHashMap<Integer, SortingState<T>> result = new LinkedHashMap<>();

        result.put(1, initialState);

        // insert algorithm implementation
        // put
        // put

        return result;
    }

}
