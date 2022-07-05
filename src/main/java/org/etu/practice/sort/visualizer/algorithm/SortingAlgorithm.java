package org.etu.practice.sort.visualizer.algorithm;

import org.etu.practice.sort.visualizer.common.SortVisualizerException;
import org.etu.practice.sort.visualizer.common.SortingState;

import java.util.Map;

public interface SortingAlgorithm<E extends Comparable<E>> {

    SortingState<E> goToFirstStep() throws SortVisualizerException;

    SortingState<E> goToLastStep() throws SortVisualizerException;

    SortingState<E> nextStep() throws SortVisualizerException;

    SortingState<E> previousStep() throws SortVisualizerException;

    void sort(SortingState<E> initialState);

    Map<E, Integer> getMapping();

}
