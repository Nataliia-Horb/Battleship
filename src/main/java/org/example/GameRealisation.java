package org.example;

import lombok.Data;

import java.util.Scanner;

@Data
public class GameRealisation {
    String name;
    char[][] field;
    Scanner scan;
    int shipCount;

    int fieldLength;

    boolean successfulStrike = true;

    public GameRealisation(String name, char[][] fieldEnemy, Scanner scan, int shipCount) {
        this.name = name;
        this.field = fieldEnemy;
        this.scan = scan;
        this.shipCount = shipCount;
        this.fieldLength = fieldEnemy.length;
    }

    public void attack() {
        int x = enterEnemyCoordinate("X");
        int y = enterEnemyCoordinate("Y");

        if (field[y][x] == '^') {
            successfulStrike = true;
            field[y][x] = 'X';
            if ((checkCoordinate(y - 1, field) && field[y - 1][x] == '^') || (checkCoordinate(y + 1, field) && field[y + 1][x] == '^')) {
                printField(field);
                System.out.println("wounded");
            } else {
                shipCount--;
                System.out.println(shipCount);
                printField(field);
                System.out.println("killed");
            }
        } else if (field[y][x] == '>') {
            successfulStrike = true;
            field[y][x] = 'X';
            if ((checkCoordinate(x - 1, field) && field[y][x - 1] == '>') || (checkCoordinate(x + 1, field) && field[y][x + 1] == '>')) {
                printField(field);
                System.out.println("wounded");
            } else {
                shipCount--;
                System.out.println(shipCount);
                printField(field);
                System.out.println("killed");

            }
        } else {
            successfulStrike = false;
            field[y][x] = '#';
            printField(field);
            System.out.println("past");
        }
    }

    private int enterEnemyCoordinate(String nameCoordinate) {
        int coordinate;
        do {
            System.out.println(name + " enter enemy " + nameCoordinate + "  coordinate: ");
            coordinate = scan.nextInt();
        }
        while (!checkCoordinate(coordinate, field));
        return coordinate;
    }


    //Проверка координаты на валидность
    public boolean checkCoordinate(int k, char[][] field) {
        if (k >= 0 && k < field.length) {
            return true;
        } else {
            System.out.println("The " + k + " is not valid");
            return false;
        }
    }

    //Печатаем поле каджый раз, когда был нанесен удар
    public static void printField(char[][] field) {
        if (field != null) {
            for (int i = 0; i < field.length; i++) {
                System.out.println();
                for (int j = 0; j < field[0].length; j++) {
                    System.out.print(field[i][j] + " ");
                }
            }
        }
        System.out.println();
    }
}
