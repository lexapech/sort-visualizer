package org.etu.practice.sort.visualizer.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;
import java.util.List;
import org.etu.practice.sort.visualizer.common.*;

public class GUI {
    private JPanel mainPanel;
    private final Map<ButtonType,JButton> buttons;

    private final Canvas canvas;
    private JComboBox<String> selectAlgorithm;
    private final List<ActionListener> generateButtonListeners;
    private final List<ActionListener> enterButtonListeners;
    private final List<ActionListener> openButtonListeners;
    private void createWindow() {
        JFrame frame = new JFrame("Sort Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1280,720));
        frame.setLocationByPlatform(true);
        frame.setMinimumSize(new Dimension(1000,400));
        mainPanel = new JPanel();
        frame.add(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.LINE_AXIS));
        mainPanel.setVisible(true);

        frame.setVisible(true);
        frame.pack();
    }
    private JPanel addBoxPanel(JPanel parent,int axis) {
        JPanel panel = new JPanel();
        parent.add(panel);
        panel.setLayout(new BoxLayout(panel,axis));
        panel.setVisible(true);
        return panel;
    }
    public void addButtonHandler(ActionListener handler,ButtonType button)
    {
        if (button == ButtonType.GENERATE_BUTTON) {
            generateButtonListeners.add(handler);
        }
        else if (button == ButtonType.ENTER_BUTTON) {
            enterButtonListeners.add(handler);
        }
        else if (button == ButtonType.OPEN_FILE_BUTTON) {
            openButtonListeners.add(handler);
        }
        else {
            buttons.get(button).addActionListener(handler);
        }
    }

    public void showMessage(String message)
    {
        JOptionPane.showMessageDialog(mainPanel,message);
    }

    public SortType getSelectedSort() {
       for (SortType s : SortType.values()) {
           if (s.getLabel().equals(selectAlgorithm.getSelectedItem())) {
               return s;
           }
       }
       return null;
    }
    public void updateArray(int[] array) {
        canvas.updateCanvas(array);
    }

    public void lockSelected(boolean lock) {
        selectAlgorithm.setEnabled(!lock);
    }

    public void markAccessed(int index) {
        canvas.markAccessed(index);
    }

    private JButton addButton(JPanel parent,ButtonType buttonType) {
        JPanel container = addBoxPanel(parent,BoxLayout.X_AXIS);
        container.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JButton button = new JButton(buttonType.getLabel());
        container.add(button);
        buttons.put(buttonType,button);
        button.setVisible(true);
        return button;
    }

    public GUI() {
        buttons = new HashMap<>();
        generateButtonListeners = new ArrayList<>();
        enterButtonListeners = new ArrayList<>();
        openButtonListeners = new ArrayList<>();
        createWindow();

        JPanel arrayPanel = addBoxPanel(mainPanel,BoxLayout.X_AXIS);
        canvas = new Canvas();
        arrayPanel.add(canvas);
        arrayPanel.setBorder(BorderFactory.createEtchedBorder());
        canvas.setVisible(true);

        JPanel controlPanel = addBoxPanel(mainPanel,BoxLayout.Y_AXIS);
        controlPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        controlPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        createInputPanel(controlPanel);
        createSelectionBox(controlPanel);
        createStartButton(controlPanel);
        createManualModePanel(controlPanel);

        controlPanel.updateUI();
    }
    private void createInputPanel(JPanel parent) {
        JPanel inputPanel = addBoxPanel(parent,BoxLayout.X_AXIS);
        inputPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        addButton(inputPanel, ButtonType.GENERATE_BUTTON);
        addButton(inputPanel, ButtonType.ENTER_BUTTON);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JButton openFileButton = addButton(inputPanel,ButtonType.OPEN_FILE_BUTTON);
        openFileButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showOpenDialog(parent);
                File file = fileChooser.getSelectedFile();
                if (file == null) return;
                openButtonListeners.forEach(x -> x.actionPerformed(new ActionEvent(file,0,null)));
            }
        });
        buttons.get(ButtonType.GENERATE_BUTTON).addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String size = JOptionPane.showInputDialog(parent,"Введите размер массива");
                if (size != null)
                    generateButtonListeners.forEach(x -> x.actionPerformed(new ActionEvent(size,0,null)));
            }
        });
        buttons.get(ButtonType.ENTER_BUTTON).addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String array = JOptionPane.showInputDialog(parent,"Введите элементы массива через пробел");
                if (array != null)
                    enterButtonListeners.forEach(x -> x.actionPerformed(new ActionEvent(array,0,null)));
            }
        });
    }
    private void createStartButton(JPanel parent) {
        JPanel startAutoButtonPanel = addBoxPanel(parent,BoxLayout.X_AXIS);
        startAutoButtonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        addButton(startAutoButtonPanel,ButtonType.START_AUTO_BUTTON);
    }
    private void createSelectionBox(JPanel parent) {
        JPanel selectAlgorithmPanel = addBoxPanel(parent,BoxLayout.X_AXIS);
        selectAlgorithmPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        selectAlgorithmPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        selectAlgorithm = new JComboBox<>();
        selectAlgorithm.addItem(SortType.BUBBLE_SORT.getLabel());
        selectAlgorithm.addItem(SortType.QUICKSORT.getLabel());
        selectAlgorithm.addItem(SortType.BITONIC_SORT.getLabel());

        selectAlgorithm.setMaximumSize(new Dimension(200,25));
        selectAlgorithmPanel.add(selectAlgorithm);
        selectAlgorithm.setVisible(true);
    }
    private void createManualModePanel(JPanel parent) {
        JPanel manualModePanel = addBoxPanel(parent,BoxLayout.X_AXIS);
        manualModePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        addButton(manualModePanel,ButtonType.PREVIOUS_STEP_BUTTON);
        addButton(manualModePanel,ButtonType.NEXT_STEP_BUTTON);
        addButton(manualModePanel,ButtonType.FIRST_STEP_BUTTON);
        addButton(manualModePanel,ButtonType.LAST_STEP_BUTTON);
    }
}
