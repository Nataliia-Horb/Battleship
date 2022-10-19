package org.example;

import java.util.*;

public class Play {
    private static final Scanner scan = new Scanner(System.in);
    private static final int FIEND_LENGTH = 10;
    private static final int[][] SHIP_DECK_AMOUNT = {{1, 4}, {2, 3}, {3, 2}, {4, 1}};
    private static final int SHIP_AMOUNT = 10;


    public static void main(String[] args) {

        String playerOneName = askNameOfPlayer("First");
        String playerTwoName = askNameOfPlayer("Second");

        Player playerOne = new Player(playerOneName, FIEND_LENGTH, SHIP_DECK_AMOUNT);
        Player playerTwo = new Player(playerTwoName, FIEND_LENGTH, SHIP_DECK_AMOUNT);
        playerOne.fillPlayerField(scan);
        playerTwo.fillPlayerField(scan);

        play(playerOne.getName(), playerTwo.getName(), playerOne.getPlayerField(), playerTwo.getPlayerField());

    }

    private static void play(String playerOne, String playerTwo, char[][] playerFieldOne, char[][] playerFieldTwo) {
        GameRealisation gamePlayerOne = new GameRealisation(playerOne, playerFieldTwo, scan, SHIP_AMOUNT);
        GameRealisation gamePlayerTwo = new GameRealisation(playerTwo, playerFieldOne, scan, SHIP_AMOUNT);

        System.out.println("START GAME");
        System.out.println("----------------------------------------------------------");

        // Участники бьются по очереди пока не выполниться условие конец игры (у одного подбили все корабли)
        while (!endCondition(gamePlayerOne.getShipCount(), gamePlayerTwo.getShipCount())) {
            executeAttacks(gamePlayerOne, gamePlayerTwo);
            executeAttacks(gamePlayerTwo, gamePlayerOne);
        }

        finishGreeting(gamePlayerOne, gamePlayerTwo);
    }

    // Пока удары успешные или пока не закончились корабли нападающий игрок атакует
    private static void executeAttacks(GameRealisation gameAssailant, GameRealisation gameAdversary) {
        do {
            if (endCondition(gameAssailant.getShipCount(), gameAdversary.getShipCount())) {
                return;
            }
            gameAssailant.attack();
        }
        while (gameAssailant.successfulStrike);
    }


    // Условие завершение игры: закончились корабли у первого или у второго игрока
    private static boolean endCondition(int shipCountOnePlayer, int shipCountTwoPlayer) {
        return (shipCountOnePlayer <= 0 || shipCountTwoPlayer <= 0);
    }


    // Приветствие игроков
    private static String askNameOfPlayer(String numberOfPlayer) {
        System.out.println(numberOfPlayer + " player, input your name: ");
        while (!scan.hasNextLine()) {
            scan.next();
            System.out.println("It does not look like a name... Try again");
            System.out.println(numberOfPlayer + " player, input your name: ");
        }
        String player = scan.nextLine();
        System.out.println("Hi " + player);
        return player;
    }


    // Поздравление победителя
    private static void finishGreeting(GameRealisation gamePlayerOne, GameRealisation gamePlayerTwo) {
        if (gamePlayerOne.getShipCount() == 0) {
            System.out.println("----------------------------------------------------------");
            System.out.println("!!! The Player " + gamePlayerOne.getName() + " won !!!");
        } else {
            System.out.println("----------------------------------------------------------");
            System.out.println("!!! The Player " + gamePlayerTwo.getName() + " won !!!");
        }
    }
}