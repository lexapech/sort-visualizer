package org.etu.practice.sort.visualizer.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;
import java.util.List;
import org.etu.practice.sort.visualizer.common.*;

import static org.etu.practice.sort.visualizer.gui.Labels.*;

public class GUIImpl implements GUI
{
    private JPanel mainPanel;
    private final Map<ButtonEnum,JButton> buttons;

    private final Canvas canvas;
    private JComboBox<String> selectAlgorithm;
    private final List<ActionListener> generateButtonListeners;
    private final List<ActionListener> enterButtonListeners;
    private final List<ActionListener> openButtonListeners;
    private void createWindow()
    {
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
    private JPanel addBoxPanel(JPanel parent,int axis)
    {
        JPanel panel = new JPanel();
        parent.add(panel);
        panel.setLayout(new BoxLayout(panel,axis));
        panel.setVisible(true);
        return panel;
    }
    public void addButtonHandler(ActionListener handler,ButtonEnum button)
    {
        if (button==ButtonEnum.GENERATE_BUTTON)
            generateButtonListeners.add(handler);
        else if (button==ButtonEnum.ENTER_BUTTON)
            enterButtonListeners.add(handler);
        else if (button==ButtonEnum.OPEN_FILE_BUTTON)
            openButtonListeners.add(handler);
        else
            buttons.get(button).addActionListener(handler);
    }

    public SortEnum getSelectedSort()
    {
       for (Map.Entry<SortEnum, String> e : sortLabels.entrySet())
       {
           if (e.getValue().equals(selectAlgorithm.getSelectedItem()))
               return e.getKey();
       }
       return null;
    }
    public void updateArray(int[] array)
    {
        canvas.updateCanvas(array);
    }

    public void lockSelected(boolean lock)
    {
        selectAlgorithm.setEnabled(!lock);
    }

    public void markAccessed(int index)
    {
        canvas.markAccessed(index);
    }

    private JButton addButton(JPanel parent,ButtonEnum buttonEnum)
    {
        JPanel container = addBoxPanel(parent,BoxLayout.X_AXIS);
        container.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JButton button= new JButton(buttonLabels.get(buttonEnum));
        container.add(button);
        buttons.put(buttonEnum,button);
        button.setVisible(true);
        return button;
    }

    public GUIImpl()
    {
        buttons = new HashMap<>();
        generateButtonListeners=new ArrayList<>();
        enterButtonListeners=new ArrayList<>();
        openButtonListeners=new ArrayList<>();
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
    private void createInputPanel(JPanel parent)
    {
        JPanel inputPanel = addBoxPanel(parent,BoxLayout.X_AXIS);
        inputPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        addButton(inputPanel,ButtonEnum.GENERATE_BUTTON);
        addButton(inputPanel,ButtonEnum.ENTER_BUTTON);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JButton openFileButton= addButton(inputPanel,ButtonEnum.OPEN_FILE_BUTTON);
        openFileButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showOpenDialog(parent);
                File file=fileChooser.getSelectedFile();
                if (file==null) return;
                openButtonListeners.forEach(x -> x.actionPerformed(new ActionEvent(file,0,null)));
            }
        });
        buttons.get(ButtonEnum.GENERATE_BUTTON).addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String size=JOptionPane.showInputDialog(parent,"Введите размер массива");
                if (size!=null)
                    generateButtonListeners.forEach(x -> x.actionPerformed(new ActionEvent(size,0,null)));
            }
        });
        buttons.get(ButtonEnum.ENTER_BUTTON).addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String array=JOptionPane.showInputDialog(parent,"Введите элементы массива через пробел");
                if (array!=null)
                    enterButtonListeners.forEach(x -> x.actionPerformed(new ActionEvent(array,0,null)));
            }
        });
    }
    private void createStartButton(JPanel parent)
    {
        JPanel startAutoButtonPanel = addBoxPanel(parent,BoxLayout.X_AXIS);
        startAutoButtonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        addButton(startAutoButtonPanel,ButtonEnum.START_AUTO_BUTTON);
    }
    private void createSelectionBox(JPanel parent)
    {
        JPanel selectAlgorithmPanel= addBoxPanel(parent,BoxLayout.X_AXIS);
        selectAlgorithmPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        selectAlgorithmPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        selectAlgorithm = new JComboBox<>();
        selectAlgorithm.addItem(sortLabels.get( SortEnum.BUBBLE_SORT));
        selectAlgorithm.addItem(sortLabels.get(SortEnum.QUICKSORT));
        selectAlgorithm.addItem(sortLabels.get(SortEnum.BITONIC_SORT));

        selectAlgorithm.setMaximumSize(new Dimension(200,25));
        selectAlgorithmPanel.add(selectAlgorithm);
        selectAlgorithm.setVisible(true);
    }
    private void createManualModePanel(JPanel parent)
    {
        JPanel manualModePanel = addBoxPanel(parent,BoxLayout.X_AXIS);
        manualModePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        addButton(manualModePanel,ButtonEnum.PREVIOUS_STEP_BUTTON);
        addButton(manualModePanel,ButtonEnum.NEXT_STEP_BUTTON);
        addButton(manualModePanel,ButtonEnum.FIRST_STEP_BUTTON);
        addButton(manualModePanel,ButtonEnum.LAST_STEP_BUTTON);
    }
}
