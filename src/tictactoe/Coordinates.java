package tictactoe;

public class Coordinates {

    private int row;
    private int column;

    public Coordinates() {
        this.row = -1;
        this.column = -1;
    }

    public Coordinates(int X, int Y) {
        this.row = X;
        this.column = Y;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int X) {
        this.row = X;
    }

    public void setColumn(int Y) {
        this.column = Y;
    }
}
