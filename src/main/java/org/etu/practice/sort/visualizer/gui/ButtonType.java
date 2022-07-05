package org.etu.practice.sort.visualizer.gui;

public enum ButtonType {
    NEXT_STEP_BUTTON {
        String getLabel() {
            return "Шаг вперед";
        }
    },
    PREVIOUS_STEP_BUTTON {
        String getLabel() {
            return "Шаг назад";
        }
    },
    FIRST_STEP_BUTTON {
        String getLabel() {
            return "Последний шаг";
        }
    },
    LAST_STEP_BUTTON {
        String getLabel() {
            return "Первый шаг";
        }
    },
    OPEN_FILE_BUTTON {
        String getLabel() {
            return "Открыть файл";
        }
    },
    GENERATE_BUTTON {
        String getLabel() {
            return "Сгенерировать";
        }
    },
    ENTER_BUTTON {
        String getLabel() {
            return "Ввести вручную";
        }
    },
    START_AUTO_BUTTON {
        String getLabel() {
            return "Запуск в автоматическом режиме";
        }
    };

    abstract  String getLabel();

}
