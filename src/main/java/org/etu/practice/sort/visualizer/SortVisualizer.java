package org.etu.practice.sort.visualizer;

import com.sun.jdi.InterfaceType;
import org.etu.practice.sort.visualizer.algorithm.BitonicSorting;
import org.etu.practice.sort.visualizer.algorithm.SortingAlgorithm;
import org.etu.practice.sort.visualizer.common.SortType;
import org.etu.practice.sort.visualizer.common.SortVisualizerException;
import org.etu.practice.sort.visualizer.common.SortingState;
import org.etu.practice.sort.visualizer.gui.ButtonType;
import org.etu.practice.sort.visualizer.gui.GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class SortVisualizer {
    private final GUI application;
    private int[] sortArray;
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
    }

    private void sort() {
        algorithm = new BitonicSorting<>();
        ArrayList<Integer> sortList = new ArrayList<>();
        for (int k : sortArray) {
            sortList.add(k);
        }
        SortingState<Integer> initState = new SortingState<>(SortType.BITONIC_SORT, sortList,new int[0]);
        algorithm.sort(initState);

    }
    private void visualize(ActionEvent event) {
        application.lockControls(true);
        thread = new Thread(() -> {
            try {
                SortingState<Integer> state;
                SortingState<Integer> last = algorithm.goToLastStep();
                state = algorithm.goToFirstStep();
                int[] resultArray = new int[state.sortingArray().size()];
                while (true) {

                    for (int i = 0; i < state.sortingArray().size(); i++) {
                        resultArray[i] = state.sortingArray().get(i);
                    }
                    application.updateArray(resultArray);
                    for (int ch : state.changedElementIndices()) {
                        application.markAccessed(ch);
                    }
                    Thread.sleep(5);
                    if (state == last) break;
                    state = algorithm.nextStep();
                }
                application.updateArray(resultArray);
                application.lockControls(false);
            }
            catch (InterruptedException | SortVisualizerException ignored) {

            }
        });
        thread.start();

    }

    private void nextStep(ActionEvent e) {
        try {
            SortingState<Integer> state = algorithm.nextStep();

            int[] resultArray = new int[state.sortingArray().size()];

            for (int i = 0; i < state.sortingArray().size(); i++) {
                resultArray[i] = state.sortingArray().get(i);
            }
            application.updateArray(resultArray);
            for (int ch : state.changedElementIndices()) {
                application.markAccessed(ch);
            }
        }
        catch (SortVisualizerException ex) {
            application.showMessage(ex.getMessage());
        }
    }

    private void interruptAnimation() {
        if (thread != null && thread.isAlive()) {
            thread.interrupt();
            application.lockControls(false);
        }
    }

    private void generateArray(ActionEvent actionEvent) {
        String source = ((String)actionEvent.getSource()).trim();
        int[] array;
        try {
            array = GenerateArray.generateArray(Integer.parseInt(source));
        } catch (NumberFormatException | NegativeArraySizeException e) {
            application.showMessage("Некорректный размер массива.");
            return;
        }
        interruptAnimation();
        sortArray = array;
        application.updateArray(sortArray);

        sort();
    }


    private void readArray(ActionEvent actionEvent) {
        String source = ((String)actionEvent.getSource()).trim();
        String[] stringArray = source.split(" ");
        int[] array = new int[stringArray.length];
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
    }

    private static class GenerateArray {
        public static int[] generateArray(int length) {
            int[] array = new int[length];
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

        public static int[] generateArray(File file) throws IOException {
            ArrayList<Integer> array = new ArrayList<>();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextInt()) {
                array.add(scanner.nextInt());
            }
            int[] intArray = new int[array.size()];
            for (int i = 0; i < array.size(); i++) {
                intArray[i] = array.get(i);
                if (intArray[i] <= 0) throw new NumberFormatException();
            }
            if (scanner.hasNext()) throw new NumberFormatException();
            return intArray;
        }
    }
}
