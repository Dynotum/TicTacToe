package tictactoe;

public class Field {
    final static String HORIZONTAL_BOUNDARIES = "---------";
    final static String VERTICAL_BOUNDARIES = "|";

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
