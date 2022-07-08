package org.etu.practice.sort.visualizer.gui;

public enum ButtonType {
    NEXT_STEP_BUTTON("Шаг вперед"),
    PREVIOUS_STEP_BUTTON("Шаг назад"),
    FIRST_STEP_BUTTON("Первый шаг"),
    LAST_STEP_BUTTON("Последний шаг"),
    OPEN_FILE_BUTTON("Открыть файл"),
    GENERATE_BUTTON("Сгенерировать"),
    ENTER_BUTTON("Ввести вручную"),
    START_AUTO_BUTTON("Запуск в автоматическом режиме");
    private final String label;
    ButtonType(String label) {
        this.label = label;
    }
    String getLabel(){
        return label;
    }

}
