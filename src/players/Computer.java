package players;

import game.Field;
import java.util.Random;

/**
 * Игрок-компьютер
 */
public class Computer extends Player {

    private char ch;
    private final Random rand;

    public Computer(String name, char ch) {
        super(name);
        this.ch = ch;
        rand = new Random();
    }

    public void setCh(char ch) {
        this.ch = ch;
    }

    @Override
    public int go(Field field) {
        char[][] localField = field.getCopy();
        int[] possibleSteps = new int[field.height * field.width];
        int steps = 0;
        for (int i = 0; i < localField.length; i++) {
            for (int j = 0; j < localField[i].length; j++) {
                if (localField[i][j] == ' ') {
                    possibleSteps[steps++] = field.width * i + j;
                }
            }
        }
        return possibleSteps[rand.nextInt(steps)];
    }
}
