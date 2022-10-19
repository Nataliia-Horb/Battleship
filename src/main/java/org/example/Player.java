package org.example;

import lombok.Data;
import org.example.validator.FieldValidation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Data
public class Player {
    private String name;

    private int fieldLength;
    private char[][] playerField;
    private int[][] shipDeckAmount;

    public Player(String name, int fieldLength, int[][] shipDeckAmount) {
        this.name = name;
        this.fieldLength = fieldLength;
        this.playerField = createEmptyField(fieldLength);
        this.shipDeckAmount = shipDeckAmount;

    }

    private char[][] createEmptyField(int fieldLength) {
        char[][] field = new char[fieldLength][fieldLength];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                field[i][j] = '-';
            }
        }
        return field;
    }

    public void fillPlayerField(Scanner scan) {
        System.out.println("----------------------------------------------------------");
        System.out.println("Player " + name + " build a field ");
        System.out.println("----------------------------------------------------------");

        // Здесь будем хранить координаты всех кораблейб для самопроверки
        List<List<Integer>> allShipsMap = new ArrayList<>();

        for (int i = 0; i < shipDeckAmount.length; i++) {
            int shipCount = shipDeckAmount[i][0];
            int shipDeck = shipDeckAmount[i][1];

            for (int j = 0; j < shipCount; j++) {
                System.out.println("Put " + shipDeck + " deck ship. Left: " + (shipCount - j));
                int x = 0;
                int y = 0;
                int direction = 0;
                int result = 1;
                FieldValidation validator = new FieldValidation(playerField, x, y, direction, shipDeck);

                //Проверяем находятся ли координаты в пределах поля
                while (result != 0) {
                    direction = giveDirection(scan, validator);
                    x = giveCoordinate(scan, validator, "X");
                    y = giveCoordinate(scan, validator, "Y");

                    // Проверяем является ли заявленая координата еще свободной
                    result = validator.validateCoordinateForShip(direction, x, y);
                }

                List OneShipMap = new ArrayList<Integer>();
                buildOneShip(direction, x, y, shipDeck, OneShipMap);
                allShipsMap.add(OneShipMap);
                GameRealisation.printField(playerField);
            }
        }
        System.out.println(allShipsMap);
    }


    // Строим один корарль и сохраняем его координаты в listMap, map для самопроверки
    private void buildOneShip(int direction, int x, int y, int shipDeck, List listMap) {
        if (direction == 1) {
            for (int k = y - 1; k <= y + 1; k++) {
                if (k >= 0 && k < fieldLength) {
                    for (int l = x - 1; l <= x + shipDeck; l++) {
                        if (l >= 0 && l < fieldLength) {
                            playerField[k][l] = '.';
                        }
                    }
                }
            }
            for (int k = 0; k < shipDeck; k++) {
                playerField[y][x + k] = '>';
                // ложим координаты одного корабля в ArrayList для самопроверки
                listMap.add(x + k);
                listMap.add(y);
            }
        } else {
            for (int k = y - 1; k <= y + shipDeck; k++) {
                if (k >= 0 && k < fieldLength) {
                    for (int l = x - 1; l <= x + 1; l++) {
                        if (l >= 0 && l < fieldLength) {
                            playerField[k][l] = '.';
                        }
                    }
                }
            }
            for (int k = 0; k < shipDeck; k++) {
                playerField[y + k][x] = '^';

                // ложим координаты одного корабля в ArrayList для самопроверки
                listMap.add(x);
                listMap.add(y + k);
            }
        }
    }

    // Спрашиваем у пользователя координату, пока он не введет допустимое значение
    private int giveCoordinate(Scanner scan, FieldValidation validator, String coordinateName) {
        int coordinate;
        do {
            System.out.println("Input start " + coordinateName + " coordinate: ");
            coordinate = scan.nextInt();
        }
        while (!validator.checkCoordinate(coordinate));
        return coordinate;
    }

    // Спрашиваем у пользователя расположение, пока он не введет 1 или 2
    private int giveDirection(Scanner scan, FieldValidation validator) {
        int direction;
        do {
            System.out.println("Input direction of ship: ");
            System.out.println("1-horizontal, 2-vertical");
            direction = scan.nextInt();
        }
        while (!validator.checkDirection(direction));
        return direction;
    }


}
