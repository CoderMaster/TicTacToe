package players;

import game.Field;

/**
 * Абстрактный игрок. Содержит имя и функцию для выполнения хода.
 */
public abstract class Player {

    private String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Функция требует игрока сделать ход. Подразумевается добросовестная
     * обработка допустимости возвращаемого значения!
     *
     * @param field ссылка на поле игры
     * @return Индекс ячейки в линейном виде.
     */
    public abstract int go(Field field);
}
