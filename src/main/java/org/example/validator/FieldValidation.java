package org.example.validator;

// Клас проверяет данные введенные пользователем и сопоставляет эти данные с требованиями и ограничениями игры
public class FieldValidation {
    char[][] field;
    int x;
    int y;
    int direction;
    int size;

    public FieldValidation(char[][] field, int x, int y, int direction, int size) {
        this.field = field;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.size = size;
    }


    //Проверка на ввод расположения корабля 1 или 2
    public boolean checkDirection(int direction) {
        if (direction != 1 && direction != 2) {
            System.out.println("Direction can only be 1 or 2");
            return false;
        }
        return true;
    }


    //Проверка на ввод координат, в пределах поля
    public boolean checkCoordinate(int k) {
        if (k >= 0 && k < field.length) {
            return true;
        } else {
            System.out.println("The " + k + " value is not valid");
            return false;
        }
    }


    // Общая проверка, устраивает ли нас данные, что ввел пользователь по
    //  direction, x, y.
    //  Если эти данные не пройдут проверку, то нужно вернуть 1- значит(будем запрашивать у пользователя новые данные)
    public int validateCoordinateForShip(int direction, int x, int y) {
        if (direction == 1) {
            return preventHorizontalDirection(x, y) ? 1 : 0;
        } else {
            return preventVerticalDirection(x, y) ? 1 : 0;
        }
    }

    // Проверка если горизонтальный корабль выходит за пределы поля, или если он размещается в уже занятых клетках,
    // тогда запрет
    private boolean preventHorizontalDirection(int x, int y) {
        if (x + size <= field[0].length) {
            return (isShipHorizontalCoordinatesTaken(x, y));
        } else {
            System.out.println("The X value is too high");
            return true;
        }
    }

    // Проверка если вертикальный корабль выходит за пределы поля, или если он размещается в уже занятых клетках,
    // тогда запрет
    private boolean preventVerticalDirection(int x, int y) {
        if (y + size <= field.length) {
            return (isShipVerticalCoordinatesTaken(x, y));
        } else {
            System.out.println("The y value is too high");
            return true;
        }
    }


    // Проверка, есть ли на месте размещения вертикального корабля хотябы одна занятая координата
    private boolean isShipVerticalCoordinatesTaken(int x, int y) {
        for (int i = y; i < y + size; i++) {
            if (isCoordinateTaken(field[i][x])) {
                System.out.println("This coordinate is already taken");
                return true;
            }
        }
        return false;
    }

    // Проверка, есть ли на месте размещения горизонтального корабля хотябы одна занятая координата
    private boolean isShipHorizontalCoordinatesTaken(int x, int y) {
        for (int i = x; i < x + size; i++) {
            if (isCoordinateTaken(field[y][i])) {
                System.out.println("This coordinate is already taken");
                return true;
            }
        }
        return false;
    }

    // Проверка, если на текущей координате уже стоит вертикальный или горизонтальный корабль или растояние
    // до ближайшего корабля недостаточно, то текущая координата уже занята
    private boolean isCoordinateTaken(char ch) {
        return (ch == '>' || ch == '^' || ch == '.');
    }


}
