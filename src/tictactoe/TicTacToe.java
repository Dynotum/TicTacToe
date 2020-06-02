package tictactoe;

import java.util.*;

public class TicTacToe {

    public void startGame() {
        final Scanner sc = new Scanner(System.in);
        final Field fieldBoard = new Field();
        System.out.print("Enter cells: ");
        final String inputString = sc.nextLine();
//        final String inputString = "_XXOO_OX_";

        final char[][] matrix = fillMatrix(inputString);

        fieldBoard.printField(inputString);


        boolean freetogo = false;
        do {
            System.out.println("Enter the coordinates:");
            String getNextMove = sc.nextLine();
//            String getNextMove = "2 3";

            final Coordinates coordinates = checkInput(getNextMove);

            String result = "";
            if (coordinates.getRow() != -1) {
                result = checkCoordinates(coordinates, matrix);
            }

            if (!result.isEmpty()) {
//                System.out.println("!!!!!!!!!!!!!!!!" + result);
                fieldBoard.printField(result);
                freetogo = true;
            }

        } while (!freetogo);

//        printResult(getResult(matrix, inputString));
    }

    private String checkCoordinates(Coordinates coordinates, char[][] matrix) {
        // 1 1 -> 2 0
        // 1 3 -> 0 0

        final int row = coordinates.getRow();
        final int column = coordinates.getColumn();
        final StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0, j = 2; i < 3 && j >= 0; i++, j--) {
            for (int k = 0; k < 3; k++) {
//                System.out.print(i + " " + k + "  ");
//                System.out.print(k + " " + j);
//                System.out.println();

                if (k == row && j == column) {
                    if (matrix[i][k] != '_') {
                        System.out.println("This cell is occupied! Choose another one!");
                        return "";
                    }
                    matrix[i][k] = 'X';

//                    System.out.println("this is my guy = matrix = " + matrix[i][k]);
                }
                stringBuilder.append(matrix[i][k]);
            }
        }

        return stringBuilder.toString();

    }

    private Coordinates checkInput(String nextLine) {
        final String[] input = nextLine.split(" ");
//        final List<Integer> coordinates = new LinkedList<>();
        Coordinates coordinates = new Coordinates();
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


    private void printResult(char result) {
        switch (result) {
            case 'X':
            case 'O':
                System.out.println(result + " wins");
                break;
            case 'd':
                System.out.println("Draw");
                break;
            case 'g':
                System.out.println("Game not finished");
                break;
            case 'i':
                System.out.println("Impossible");
                break;
        }
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

        for (char[] row : matrix) {
            for (char col : row) {
                if (col == '_') {
                    return 'g';
                }
            }
        }

        // return draw
        return 'd';
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
//            System.out.println(elementFound);
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
//                System.out.print(matrix[n][m]);
            }
//            System.out.println();
        }

/*        System.out.println();
        for (int n = 0, m = 2; m > -1; n++) {
            System.out.print("( " + n + " , " + m + ")");
            if (n == 2) {
                m--;
                n = -1;
                System.out.println();
//                System.out.print("( " + n + " , " + m + ")");
            }
        }*/

        return matrix;
    }


}