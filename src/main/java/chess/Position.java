package chess;

import java.util.Optional;

/**
 * Describes a position on the Chess Board
 */
public class Position {
    public static final int MIN_ROW = 1;
    public static final int MAX_ROW = 8;
    public static final char MIN_COLUMN = 'a';
    public static final char MAX_COLUMN = 'h';
    private int row;
    private char column;

    /**
     * Create a new position object
     *
     * @param column The column
     * @param row The row
     */
    public Position(char column, int row) {
        this.row = row;
        this.column = column;
    }

    /**
     * Create a new Position object by parsing the string
     * @param colrow The column and row to use.  I.e. "a1", "h7", etc.
     */
    public Position(String colrow) {
        this(colrow.toCharArray()[0], Character.digit(colrow.toCharArray()[1], 10));
    }

    public int getRow() {
        return row;
    }

    public char getColumn() {
        return column;
    }

    public Optional<Position> up() {
        Position position = new Position(getColumn(), getRow() + 1);
        if (position.outOfBoard()) {
            return Optional.empty();
        }
        return Optional.of(position);
    }

    public Optional<Position> down() {
        Position position = new Position(getColumn(), getRow() - 1);
        if (position.outOfBoard()) {
            return Optional.empty();
        }
        return Optional.of(position);
    }

    public Position left() {
        Character leftColumn = Column.left(getColumn());
        if (leftColumn != null) {
            return new Position(leftColumn, getRow());
        }
        return null;
    }

    public Position right() {
        Character rightColumn = Column.right(getColumn());
        if (rightColumn != null) {
            return new Position(rightColumn, getRow());
        }
        return null;
    }

    public Optional<Position> upAndLeft() {
        return up().map(Position::left);
    }

    public Optional<Position> upAndRight() {
        return up().map(Position::right);
    }

    public Optional<Position> downAndRight() {
        return down().map(Position::right);
    }

    public Optional<Position> downAndLeft() {
        return down().map(Position::left);
    }

    private boolean outOfBoard() {
        return getRow() < 1 || getRow() > 8;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (column != position.column) return false;

        //noinspection RedundantIfStatement
        if (row != position.row) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + (int) column;
        return result;
    }

    @Override
    public String toString() {
        return "" + column + row;
    }

}
