package main;

import game.Field;
import game.Game;
import java.util.Scanner;
import players.Computer;
import players.Human;
import players.Player;

/**
 * Игра в крестики-нолики. Все настройки игры производить через параметры
 * запуска. Описание параметров запуска см. showHelp(). Значения по умолчанию:
 * -p PvsC -s 3x3 -w 3. (Если Field.MIN_FIELD_SIZE=3). Меньшее поле или длину
 * победной линии задавать нельзя.<br>
 * email: supermegacoder@gmail.com
 */
public class Main {

    private static Player player1, player2;
    private static int w, x, y;

    public static void main(String[] args) {
        if (parseParam(args)) {
            Game game = new Game(player1, player2, x, y, w);
            game.start();
        } else {
            showHelp();
        }
    }

    private static void showHelp() {
        System.out.println("Ошибка параметров командной строки!");
        System.out.println("Допустимые параметры:");
        System.out.println("-p *vs* | выбор режима игры, где * означает P - игрок, C - компьютер.");
        System.out.println("-s *x*  | выбор размера игрового поля, где * - число");
        System.out.println("-w *    | длина линии для выигрыша, где * - число");
    }

    /**
     *
     * @param args
     * @return true - параметры без ошибок
     */
    private static boolean parseParam(String[] args) {
        if (args.length != 0 && args.length != 2 && args.length != 4 && args.length != 6) {
            return false;
        }
        player1 = new Human("Player");
        player2 = new Computer("Computer", 'O');
        x = y = w = Field.MIN_FIELD_SIZE;
        int i = 0;
        boolean _p, _s, _w;
        _p = _s = _w = false;
        while (i < args.length) {
            switch (args[i]) {
                case "-p":
                    if (_p || !setPlayers(args[i + 1])) {
                        return false;
                    }
                    _p = true;
                    break;
                case "-s":
                    if (_s || !setSize(args[i + 1])) {
                        return false;
                    }
                    _s = true;
                    break;
                case "-w":
                    if (_w || !setWinLine(args[i + 1])) {
                        return false;
                    }
                    _w = true;
                    break;
                default:
                    return false;
            }
            i += 2;
        }
        return true;
    }

    /**
     * Устанавливает игроков согласно параметру. Требуется, чтобы player1 уже
     * был настроен на человека, а player2 на компьютера
     *
     * @param string
     * @return
     */
    private static boolean setPlayers(String string) {
        switch (string) {
            case "PvsP":
                player1.setName("Player 1");
                player2 = new Human("Player 2");
                break;
            case "PvsC":
                //устанавливается по умолчанию
                break;
            case "CvsP":
                Player temp = player1;
                player1 = player2;
                player2 = temp;
                ((Computer) player1).setCh('X');
                break;
            case "CvsC":
                player1 = new Computer("Computer 1", 'X');
                player2.setName("Computer 2");
                break;
            default:
                return false;
        }
        return true;
    }

    private static boolean setWinLine(String string) {
        try {
            w = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private static boolean setSize(String string) {
        int ind = string.indexOf("x");
        if (ind == -1) {
            return false;
        }
        try {
            x = Integer.parseInt(string.substring(0, ind));
            y = Integer.parseInt(string.substring(ind + 1));
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
