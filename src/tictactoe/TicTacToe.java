package tictactoe;

import java.util.*;

public class TicTacToe {
    final static String HORIZONTAL_BOUNDARIES = "---------";
    final static String VERTICAL_BOUNDARIES = "|";

    public void startGame() {
        final Scanner sc = new Scanner(System.in);
        System.out.print("Enter cells: ");
//        final String inputString = sc.nextLine();
        final String inputString = "X_X_O____";

        final char[][] matrix = fillMatrix(inputString);
        printField(inputString);

        System.out.println("Enter the coordinates:");

        boolean freetogo = true;
        do {
//            String getNextMove = sc.nextLine();
            String getNextMove = "1 1";

            final List<Integer> coordinates = checkInput(getNextMove);

            if (coordinates.isEmpty()) {
                //todo
                // ya tengo los numeros ahora es comparar que las coordenadas
                // no esten ocupadas

//                checkCoordinates(coordinates, matrix);

            }


        } while (freetogo);

//        printResult(getResult(matrix, inputString));
    }

    private void checkCoordinates(List<Integer> coordinates, char[][] matrix) {
        // recuerda que estan invertidas
        // 1 1 -> 2 0
        // 1 3 -> 0 0


        int coo1 = coordinates.get(0);
        int coo2 = coordinates.get(1);

    }

    private List<Integer> checkInput(String nextLine) {
        final String[] input = nextLine.split(" ");
        final List<Integer> coordinates = new LinkedList<>();
        try {
            final int number1 = Integer.parseInt(input[0]);
            final int number2 = Integer.parseInt(input[1]);

            if (number1 <= 4 && number1 >= 0 && number2 <= 4  && number2 >= 0) {
                System.out.println("Coordinates should be from 1 to 3!");
                return Collections.emptyList();
            }
            coordinates.add(number1);
            coordinates.add(number2);

        } catch (NumberFormatException e) {
            System.out.println("You should enter numbers!");
            return Collections.emptyList();
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
        return matrix;
    }


    public void printField(String inputString) {
        char[] input = inputString.toCharArray();
        int counter = 0;
        final StringBuilder field = new StringBuilder();
        System.out.println();
        // X -> horizontal
        // Y -> vertical
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 9; x++) {
                // append horizontal boundaries
                if ((x == 0 && y == 0)) {
                    field.append(HORIZONTAL_BOUNDARIES).append("\n");
                    x = 9;
                    continue;
                }

                if (x == 0) {
                    field.append(VERTICAL_BOUNDARIES);
                    continue;
                }
                if (x == 8 && y > 0) {
                    field.append(VERTICAL_BOUNDARIES).append("\n");
                    continue;
                }

                if (x % 2 == 0) {
                    field.append(input[counter++]);
                } else {
                    field.append(" ");
                }
            }
        }
        System.out.println(field.append(HORIZONTAL_BOUNDARIES));
    }


}