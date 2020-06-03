package tictactoe;

import java.util.*;

public class TicTacToe {

    public void startGame() {
        final Scanner sc = new Scanner(System.in);
        final Field fieldBoard = new Field();
        final String input = "_________";
        final char[][] matrix = fillMatrix(input);
        boolean freetogo = false;
        int counter = -1;

        fieldBoard.printField(input);

        do {
            char inputUser = counter % 2 == 0 ? 'X' : 'O';
            System.out.println("Enter the coordinates:");
            final String getNextMove = sc.nextLine();
            final Coordinates coordinates = checkInput(getNextMove);

            String result = "";
            if (coordinates.getRow() != -1) {
                result = checkCoordinates(coordinates, matrix, inputUser);
            }

            if (!result.isEmpty()) {
                fieldBoard.printField(result);
                freetogo = printResult(getResult(fillMatrix(result), input));
                counter++;
            }
        } while (!freetogo);
    }

    private String checkCoordinates(Coordinates coordinates, char[][] matrix, char inputUser) {
        // 1 1 -> 2 0
        // 1 3 -> 0 0
        final int row = coordinates.getRow();
        final int column = coordinates.getColumn();
        final StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0, j = 2; i < 3 && j >= 0; i++, j--) {
            for (int k = 0; k < 3; k++) {
                if (k == row && j == column) {
                    if (matrix[i][k] != '_') {
                        System.out.println("This cell is occupied! Choose another one!");
                        return "";
                    }
                    matrix[i][k] = inputUser;
                }
                stringBuilder.append(matrix[i][k]);
            }
        }
        return stringBuilder.toString();
    }

    private Coordinates checkInput(String nextLine) {
        final String[] input = nextLine.split(" ");
        final Coordinates coordinates = new Coordinates();
        try {
            final int number1 = Integer.parseInt(input[0]);
            final int number2 = Integer.parseInt(input[1]);

            if ((number1 < 1 || number1 > 3) || (number2 < 1 || number2 > 3)) {
                System.out.println("Coordinates should be from 1 to 3!");
                return coordinates;
            }
            coordinates.setRow(number1 - 1);
            coordinates.setColumn(number2 - 1);

        } catch (NumberFormatException e) {
            System.out.println("You should enter numbers!");
            return coordinates;
        }

        return coordinates;
    }

    private boolean printResult(char result) {
        switch (result) {
            case 'X':
            case 'O':
                System.out.println(result + " wins");
                return true;
            case 'd':
                System.out.println("Draw");
                return true;
            case 'g':
                System.out.println("Game not finished");
                return true;
            case 'i':
                System.out.println("Impossible");
                return true;
        }
        return false;
    }

    private boolean checkImpossible(String impossible2) {
        int numberX = 0;
        int numberO = 0;

        final char[] arrayChar = impossible2.toCharArray();

        for (char element : arrayChar) {

            if (element == 'X') numberX++;
            if (element == 'O') numberO++;
        }
        return Math.abs(numberO - numberX) >= 2;
    }

    private char getResult(char[][] matrix, String inputString) {
        if (checkImpossible(inputString)) {
            return 'i';
        }

        // rows
        for (int i = 0; i < 3; i++) {
            if (matrix[i][1] == matrix[i][0] &&
                    matrix[i][1] == matrix[i][2]) {
                if (checkAgainRows(matrix, matrix[i][i])) {
                    return 'i';
                }
                return matrix[i][i];
            }
        }

        // colums
        for (int i = 0; i < 3; i++) {
            if (matrix[1][i] == matrix[0][i] &&
                    matrix[1][i] == matrix[2][i]) {
                if (checkAgainCol(matrix, matrix[i][i])) {
                    return 'i';
                }
                return matrix[i][i];
            }
        }

        // diagonal
        if (matrix[1][1] == matrix[0][0] && matrix[1][1] == matrix[2][2] ||
                matrix[1][1] == matrix[0][2] && matrix[1][1] == matrix[2][0]) {
            return matrix[1][1];
        }

        int counter = 0;
        for (char[] row : matrix) {
            for (char col : row) {
                if (col == '_') {
                    counter++;
                }
            }
        }
        //return draw
        if (counter == 0) {
            return 'd';
        }

        return 'h';
    }

    private boolean checkAgainCol(char[][] matrix, char elementFound) {
        for (int i = 0; i < 3; i++) {
            if (elementFound != matrix[1][i] && matrix[1][i] == matrix[0][i] &&
                    matrix[1][i] == matrix[2][i]) {
                return true;
            }
        }
        return false;
    }

    private boolean checkAgainRows(char[][] matrix, char elementFound) {
        // rows
        for (int i = 0; i < 3; i++) {
            if (matrix[i][1] != elementFound && matrix[i][1] == matrix[i][0] &&
                    matrix[i][1] == matrix[i][2]) {
                return true;
            }
        }
        return false;
    }

    private char[][] fillMatrix(String inputString) {
        char[] input = inputString.toCharArray();
        char[][] matrix = new char[3][3];
        int counter = 0;

        for (int n = 0; n < 3; n++) {
            for (int m = 0; m < 3; m++) {
                matrix[n][m] = input[counter++];
            }
        }
        return matrix;
    }


}