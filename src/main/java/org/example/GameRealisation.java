package org.example;

import lombok.Data;

import java.util.Arrays;

public class Battlefield {
    char [][] field;
    boolean check;

    public Battlefield(int size) {
        this.field = new char[size][size];
    }
    private char[][] createEmptyField() {
         for (int i=0; i< field.length; i++) {
             for (int j=0; j< field[0].length; j++) {
                 field [i][j]='-';
             }
         }
         return field;
    }
    /*public char [][] createHorizontalShip( Ship oneShip ) {
check=false;
        char[][]correctField= copyField(field);
        for (int i = oneShip.getStartPositionY() - 1; i <= oneShip.getStartPositionY() + 1; i++) {
            if (i>=0 && i< correctField.length) {
                for (int j = oneShip.getStartPositionX() - 1; j <= oneShip.getStartPositionX() + oneShip.getSize(); j++) {
                    if (j >= 0  && j<correctField[0].length) {
                        if (correctField[i][j]!='-' && correctField[i][j]!='.' ) {
                            System.out.println();
                            System.out.println("This part of the field is already occupied");
                            check=true;
                            return null;
                        }
                        correctField[i][j] = '.';
                    }
                }
            }
        }
        for (int j = oneShip.getStartPositionX(); j < oneShip.getStartPositionX() + oneShip.getSize(); j++) {
            correctField[oneShip.getStartPositionY()][j] = '*';
        }
return correctField;
        }

  public char [][] createVerticalShip( Ship oneShip ) {
      char[][]correctField= copyField(field);
      check=false;
            for (int i = oneShip.getStartPositionY() - 1; i <= oneShip.getStartPositionY() + oneShip.getSize(); i++) {
                if (i >= 0 && i < correctField.length) {
                    for (int j = oneShip.getStartPositionX() - 1; j <= oneShip.getStartPositionX() + 1; j++) {
                        if (j >= 0 && j < correctField[0].length) {
                            if (correctField[i][j]!='-' && correctField[i][j]!='.' ) {
                                System.out.println();
                                System.out.println("This part of the field is already occupied");
                                check=true;
                                return null;
                            }
                            correctField[i][j] = '.';
                        }
                    }
                }
            }
            for (int i = oneShip.getStartPositionY(); i < oneShip.getStartPositionY() + oneShip.getSize(); i++) {
                correctField[i][oneShip.getStartPositionX()] =  '*';
            }
            return correctField;
        }


    public void print( char [][] field) {
        if (field!=null) {
            for (int i = 0; i < field.length; i++) {
                System.out.println();
                for (int j = 0; j < field[0].length; j++) {
                    System.out.print(field[i][j] + " ");
                }
            }
        }
        System.out.println();
    }

    public void setField(char[][] correctField) {
        if (correctField!= null) {
            this.field = copyField(correctField);
        }
    }*/


}
