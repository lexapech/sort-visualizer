package org.etu.practice.sort.visualizer.gui;

import java.util.Map;
import static java.util.Map.entry;
import org.etu.practice.sort.visualizer.common.*;
public class Labels
{
    public static Map<ButtonEnum, String> buttonLabels= Map.ofEntries(

            entry(ButtonEnum.OPEN_FILE_BUTTON,"Открыть файл"),
            entry(ButtonEnum.GENERATE_BUTTON,"Сгенерировать"),
            entry(ButtonEnum.ENTER_BUTTON,"Ввести вручную"),
            entry(ButtonEnum.START_AUTO_BUTTON,"Запуск в автоматическом режиме"),
            entry(ButtonEnum.NEXT_STEP_BUTTON,"Шаг вперед"),
            entry(ButtonEnum.PREVIOUS_STEP_BUTTON,"Шаг назад"),
            entry(ButtonEnum.FIRST_STEP_BUTTON,"Первый шаг"),
            entry(ButtonEnum.LAST_STEP_BUTTON,"Последний шаг")
);
    public static Map<SortEnum, String> sortLabels= Map.ofEntries(
        entry(SortEnum.BUBBLE_SORT,"Сортировка пузырьком"),
        entry(SortEnum.QUICKSORT,"Быстрая сортировка"),
        entry(SortEnum.BITONIC_SORT,"Битонная сортировка")
);
}
