package org.etu.practice.sort.visualizer.algorithm;

import java.util.List;

public interface SortingAlgorithm {

    SortingState goToFirstStep(List<SortingState> states);

    SortingState goToLastStep(List<SortingState> states);

    SortingState nextStep();

    SortingState previousStep();

    List<SortingState> sort(SortingState initialState);

}
