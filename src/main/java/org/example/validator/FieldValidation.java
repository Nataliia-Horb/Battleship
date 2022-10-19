package org.example.validator;

public class fieldValidation {
    char[][] field;
    int x;
    int y;
    int direction;
    int size;
    public fieldValidation(char[][] field, int x, int y, int direction, int size ) {
        this.field = copyField(field);
        this.x = x;
        this.y = y;
        this.direction=direction;
        this.size=size;
    }

    public int checkStartCoordinate () {
        if (x<0 || x>=field.length) {
            return 0;
        }
        if (y<0 || y>=field.length) {
            return 0;
        }
        else return 1;
    }

    public int checkDirection () {
        for (int i = y - 1; i <= y + 1; i++) {
                for (int j = x - 1; j <= x + size; j++) {
                        if (field[i][j]!='-') {
                            return 0;
                        }
                    }
                }
        return 1;
    }


    public char[][] copyField(char[][] field) {
        int length= field.length;
        char [][]fieldCopy=new char[field.length][field[0].length];
        for (int i=0; i<length; i++) {
            System.arraycopy(field[i], 0, fieldCopy[i], 0, field[i].length);
        }
        return fieldCopy;
    }


}
