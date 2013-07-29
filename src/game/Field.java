package game;

import java.util.Arrays;

/**
 * Игровое поле. Особенность класса в том, что имеется пакетный доступ для
 * записи в поле. Для остальных классов - только чтение. О механике игры игровое
 * поле ничего не знает.
 */
public class Field {

    public static final int ERROR_OUT_OF_BOUNDS = -1;
    public static final int ERROR_USED_CELL = -2;
    public static final int NO_ERROR = 1;
    public static final int MIN_FIELD_SIZE = 3;
    public static final char EMPTY_CELL = ' ';
    final char[][] field;
    public final int width, height;

    Field(int size) {
        this(size, size);
    }

    Field() {
        this(MIN_FIELD_SIZE);
    }

    Field(int x, int y) {
        if (x < MIN_FIELD_SIZE) {
            x = MIN_FIELD_SIZE;
        }
        if (y < MIN_FIELD_SIZE) {
            y = MIN_FIELD_SIZE;
        }
        field = new char[x][y];
        width = field[0].length;
        height = field.length;
        fieldInit();
    }

    /**
     * Заполняет игровое поле пустыми клетками - пробелами
     */
    private void fieldInit() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = EMPTY_CELL;
            }
        }
    }

    /**
     * Выводит в стандартный поток вывода игровое поле
     */
    void showField() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print("[" + field[i][j] + "]");
            }
            System.out.println();
        }
    }

    /**
     * Проверяет, что ход в указанную клетку возможен.
     *
     * @param x
     * @param y
     * @return ERROR_OUT_OF_BOUNDS: выход за пределы игрового поля;
     * ERROR_USED_CELL: клета занята; NO_ERROR: свободно
     */
    public int validationStep(int x, int y) {
        if (x < 0 || x >= field.length || y < 0 || y >= field[0].length) {
            return ERROR_OUT_OF_BOUNDS;
        }
        if (field[x][y] != EMPTY_CELL) {
            return ERROR_USED_CELL;
        }
        return NO_ERROR;
    }

    public char[][] getCopy() {
        char[][] res = new char[field.length][];
        for (int i = 0; i < res.length; i++) {
            res[i] = Arrays.copyOf(field[i], field[i].length);
        }
        return res;
    }
}
