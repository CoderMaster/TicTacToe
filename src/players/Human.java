package players;

import game.Field;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Игрок-человек
 */
public class Human extends Player {

    private final Scanner scanner;

    public Human(String name, Scanner scanner) {
        super(name);
        this.scanner = scanner;
    }

    @Override
    public int go(Field field) {
        while (true) {
            System.out.print("Введите координаты клетки: ");
            int x = getInt() - 1;
            int y = getInt() - 1;
            int valid = field.validationStep(x, y);
            if (valid == Field.EMPTY_CELL) {
                return field.width * x + y;
            } else if (valid == Field.ERROR_OUT_OF_BOUNDS) {
                System.out.println("Ошибочные координаты!");
            } else if (valid == Field.ERROR_USED_CELL) {
                System.out.println("Клетка занята!");
            } else {
                System.out.println("Неизвестная ошибка!");
            }
        }
    }

    /**
     * Возвращает целое число из потока scanner
     *
     * @return
     */
    private int getInt() {
        int i = 0;
        boolean goodToken = false;
        while (!goodToken) {
            goodToken = true;
            try {
                i = scanner.nextInt();
            } catch (InputMismatchException e) {
                goodToken = false;
                System.out.println("Ошибка ввода! Нужно целое число! " + e);
                //пропускаем токен
                scanner.next();
            }
        }
        return i;
    }
}
