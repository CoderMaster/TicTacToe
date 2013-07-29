package players;

import game.Field;
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
            if (valid == Field.NO_ERROR) {
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
        while (!scanner.hasNextInt()) {
            System.out.println("Ошибка! Нужно целое число.");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
