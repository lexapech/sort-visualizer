package org.etu.practice.sort.visualizer;

import org.etu.practice.sort.visualizer.gui.ButtonType;
import org.etu.practice.sort.visualizer.gui.GUI;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class SortVisualizer {
    private final GUI application;
    private int[] sortArray;

    public static void main(String[] argv) {
        SortVisualizer sortVisualizer = new SortVisualizer();
    }

    public SortVisualizer() {
        application = new GUI();
        application.addButtonHandler(this::generateArray, ButtonType.GENERATE_BUTTON);
        application.addButtonHandler(this::readArray, ButtonType.ENTER_BUTTON);
        application.addButtonHandler(this::getArrayFromFile, ButtonType.OPEN_FILE_BUTTON);
    }

    private void getArrayFromFile(ActionEvent actionEvent) {
        File file = (File) actionEvent.getSource();
        try {
            sortArray = GenerateArray.generateArray(file);
        } catch (IOException e) {
            application.showMessage("Файл не найден.");
            return;
        }
        application.updateArray(sortArray);
    }

    private void generateArray(ActionEvent actionEvent) {
        String source = ((String)actionEvent.getSource()).trim();
        int[] array;
        try {
            array = GenerateArray.generateArray(Integer.parseInt(source));
        } catch (NumberFormatException e) {
            application.showMessage("Некорректный размер массива.");
            return;
        }
        sortArray = array;
        application.updateArray(sortArray);
    }

    private void readArray(ActionEvent actionEvent) {
        String source = ((String)actionEvent.getSource()).trim();
        String[] stringArray = source.split(" ");
        int[] array = new int[stringArray.length];
        try {
            for (int i = 0; i < array.length; i++) {
                array[i] = Integer.parseInt(stringArray[i]);
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
            }
            return intArray;
        }
    }
}
