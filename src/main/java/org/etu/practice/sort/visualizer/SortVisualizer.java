package org.etu.practice.sort.visualizer;

import org.etu.practice.sort.visualizer.algorithm.SortingAlgorithm;
import com.sun.jdi.InterfaceType;
import org.etu.practice.sort.visualizer.algorithm.BitonicSorting;
import org.etu.practice.sort.visualizer.algorithm.SortingAlgorithm;
import org.etu.practice.sort.visualizer.common.SortType;
import org.etu.practice.sort.visualizer.exception.SortVisualizerException;
import org.etu.practice.sort.visualizer.state.SortingState;
import org.etu.practice.sort.visualizer.gui.ButtonType;
import org.etu.practice.sort.visualizer.gui.GUI;
import org.etu.practice.sort.visualizer.state.SortingState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;


public class SortVisualizer {
    private final GUI application;
    private Integer[] sortArray;
    private SortingAlgorithm<Integer> algorithm;
    private Thread thread;
    public static void main(String[] argv) {
        SortVisualizer sortVisualizer = new SortVisualizer();
    }

    public SortVisualizer() {
        application = new GUI();
        application.addButtonHandler(this::generateArray, ButtonType.GENERATE_BUTTON);
        application.addButtonHandler(this::readArray, ButtonType.ENTER_BUTTON);
        application.addButtonHandler(this::getArrayFromFile, ButtonType.OPEN_FILE_BUTTON);
        application.addButtonHandler(this::visualize, ButtonType.START_AUTO_BUTTON);
        application.addButtonHandler(this::nextStep, ButtonType.NEXT_STEP_BUTTON);
        application.addButtonHandler(this::firstStep, ButtonType.FIRST_STEP_BUTTON);
        application.addButtonHandler(this::lastStep, ButtonType.LAST_STEP_BUTTON);
        application.addButtonHandler(this::previousStep, ButtonType.PREVIOUS_STEP_BUTTON);
        application.addSelectorHandler(this::sort);
    }

    private void nextStep(ActionEvent e) {
        try {
            SortingState<Integer> state = algorithm.nextStep();

            application.updateArray(state.sortingArray());
            for (int ch : state.changedElementIndices()) {
                application.markAccessed(ch);
            }
        }
        catch (SortVisualizerException ex) {
            application.showMessage(ex.getMessage());
        }
    }

    private void previousStep(ActionEvent event) {
        try {
            SortingState<Integer> state = algorithm.previousStep();

            application.updateArray(state.sortingArray());
            for (int ch : state.changedElementIndices()) {
                application.markAccessed(ch);
            }
        }
        catch (SortVisualizerException ex) {
            application.showMessage(ex.getMessage());
        }
    }

    private void lastStep(ActionEvent event) {
        try {
            SortingState<Integer> state = algorithm.goToLastStep();

            application.updateArray(state.sortingArray());
            for (int ch : state.changedElementIndices()) {
                application.markAccessed(ch);
            }
        }
        catch (SortVisualizerException ex) {
            application.showMessage(ex.getMessage());
        }
    }

    private void firstStep(ActionEvent event) {
        try {
            SortingState<Integer> state = algorithm.goToFirstStep();

            application.updateArray(state.sortingArray());
            for (int ch : state.changedElementIndices()) {
                application.markAccessed(ch);
            }
        }
        catch (SortVisualizerException ex) {
            application.showMessage(ex.getMessage());
        }
    }

    private void sort(ActionEvent event) {
        algorithm = application.getSelectedSort().getIntegerSorting();
        SortingState<Integer> initState = new SortingState<>(sortArray,new int[0]);
        algorithm.sort(initState);

    }
    private void visualize(ActionEvent event) {
        application.lockControls(true);
        thread = new Thread(() -> {
            try {
                SortingState<Integer> state;
                SortingState<Integer> last = algorithm.goToLastStep();
                state = algorithm.goToFirstStep();
                while (true) {

                    application.updateArray(state.sortingArray());
                    for (int ch : state.changedElementIndices()) {
                        application.markAccessed(ch);
                    }
                    Thread.sleep(5);
                    if (state == last) break;
                    state = algorithm.nextStep();
                }
                application.updateArray(state.sortingArray());
                application.lockControls(false);
            }
            catch (InterruptedException | SortVisualizerException ignored) {

            }
        });
        thread.start();

    }

    private void interruptAnimation() {
        if (thread != null && thread.isAlive()) {
            thread.interrupt();
            application.lockControls(false);
        }
    }

    private void getArrayFromFile(ActionEvent actionEvent) {
        File file = (File) actionEvent.getSource();
        try {
            sortArray = GenerateArray.generateArray(file);
        } catch (IOException e) {
            application.showMessage("Файл не найден.");
            return;
        } catch (NumberFormatException e) {
            application.showMessage("Некорректный массив.");
            return;
        }
        application.updateArray(sortArray);
        sort(null);
    }

    private void generateArray(ActionEvent actionEvent) {
        String source = ((String)actionEvent.getSource()).trim();
        Integer[] array;
        try {
            array = GenerateArray.generateArray(Integer.parseInt(source));
        } catch (NumberFormatException | NegativeArraySizeException e) {
            application.showMessage("Некорректный размер массива.");
            return;
        }
        interruptAnimation();
        sortArray = array;
        application.updateArray(sortArray);
        sort(null);
    }


    private void readArray(ActionEvent actionEvent) {
        String source = ((String)actionEvent.getSource()).trim();
        String[] stringArray = source.split(" ");
        Integer[] array = new Integer[stringArray.length];
        try {
            for (int i = 0; i < array.length; i++) {
                array[i] = Integer.parseInt(stringArray[i]);
                if (array[i] <= 0) throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            application.showMessage("Неправильно введен массив.");
            return;
        }
        sortArray = array;
        application.updateArray(sortArray);
        sort(null);
    }

    private static class GenerateArray {
        public static Integer[] generateArray(int length) {
            Integer[] array = new Integer[length];
            for (int i = 0; i < length; i++) {
                array[i] = i+1;
            }
            for (int i = 0,j,k; i < Math.pow(length,2); i++) {
                j = (int)(Math.random()*length);
                k = (int)(Math.random()*length);
                int buffer = array[j];
                array[j] = array[k];
                array[k] = buffer;
            }

            return array;
        }

        public static Integer[] generateArray(File file) throws IOException {
            ArrayList<Integer> array = new ArrayList<>();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextInt()) {
                array.add(scanner.nextInt());
            }
            Integer[] intArray = new Integer[array.size()];
            for (int i = 0; i < array.size(); i++) {
                intArray[i] = array.get(i);
                if (intArray[i] <= 0) throw new NumberFormatException();
            }
            if (scanner.hasNext()) throw new NumberFormatException();
            return intArray;
        }
    }
}
