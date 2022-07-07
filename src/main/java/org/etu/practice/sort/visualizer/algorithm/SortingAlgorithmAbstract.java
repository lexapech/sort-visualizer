package org.etu.practice.sort.visualizer.algorithm;

import org.etu.practice.sort.visualizer.state.SortingState;
import org.etu.practice.sort.visualizer.exception.SortVisualizerException;
import java.util.*;

public abstract class SortingAlgorithmAbstract<T extends Comparable<T>> implements SortingAlgorithm<T> {

    private final Map<T, Integer> mapping = new HashMap<>();
    private int currentStep = 1;

    private LinkedHashMap<Integer, SortingState<T>> result;

    @Override
    public final SortingState<T> goToFirstStep() throws SortVisualizerException {
        if (result != null && !result.isEmpty()) {
            currentStep = 1;
            return result.get(currentStep);
        } else {
            throw new SortVisualizerException("Сортировка не была выполнена.");
        }
    }

    @Override
    public final SortingState<T> goToLastStep() throws SortVisualizerException {
        if (result != null && !result.isEmpty()) {
            currentStep = result.size();
            return result.get(currentStep);
        } else {
            throw new SortVisualizerException("Сортировка не была выполнена.");
        }
    }

    @Override
    public final SortingState<T> nextStep() throws SortVisualizerException {
        if (result != null && !result.isEmpty() && currentStep < result.size()) {
            return result.get(++currentStep);
        } else {
            throw new SortVisualizerException("Невозможно перейти на следующий шаг или сортировка не была выполнена.");
        }
    }

    @Override
    public final SortingState<T> previousStep() throws SortVisualizerException {
        if (result != null && !result.isEmpty() && currentStep > 1) {
            return result.get(--currentStep);
        } else {
            throw new SortVisualizerException("Невозможно перейти на предыдущий шаг или сортировка не была выполнена.");
        }
    }

    @Override
    public final void sort(SortingState<T> initialState) {
       // prepareMapping(initialState);
        result = startSortAlgorithm(initialState);
    }

    private void prepareMapping(SortingState<T> initialState) {

        List<T> listForSorting = Arrays.asList(initialState.sortingArray());

        listForSorting.sort(new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o1.compareTo(o2);
            }
        });

        int barHeight = 0;
        for (T element : listForSorting) {
            if (!mapping.containsKey(element)) {
                mapping.put(element, ++barHeight);
            }
        }
    }

    protected abstract LinkedHashMap<Integer, SortingState<T>> startSortAlgorithm(SortingState<T> initialState);

    protected void swap(T[] arr, int i, int j) {
        T buf;
        buf = arr[i];
        arr[i] = arr[j];
        arr[j] = buf;
    }
    @Override
    public Map<T, Integer> getMapping() {
        return mapping;
    }

}
