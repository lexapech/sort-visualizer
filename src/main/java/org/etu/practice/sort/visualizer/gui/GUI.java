package org.etu.practice.sort.visualizer.gui;

import java.awt.event.ActionListener;
import org.etu.practice.sort.visualizer.common.*;
public interface GUI {
    void addButtonHandler(ActionListener handler, ButtonEnum button);
    SortEnum getSelectedSort();
    void updateArray(int[] array);
    void markAccessed(int index);
    void lockSelected(boolean lock);
}
