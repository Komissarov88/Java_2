package hw2;

public class ArrayDataException extends IllegalArgumentException{

    private int col;
    private int row;

    public ArrayDataException(String msg, int row, int col) {
        super(msg);
        this.col = col;
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
