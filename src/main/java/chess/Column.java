package chess;

public enum Column {

    A(1, 'a'),
    B(2, 'b'),
    C(3, 'c'),
    D(4, 'd'),
    E(5, 'e'),
    F(6, 'f'),
    G(7, 'g'),
    H(8, 'h');

    Column(int number, Character letter) {
        this.number = number;
        this.letter = letter;
    }

    private int number;
    private Character letter;

    public int getNumber() {
        return number;
    }

    public Character getLetter() {
        return letter;
    }

    public static Character left(Character current) {
        int leftPosition = find(current).getNumber() - 1;
        if (outOfBoard(leftPosition)) {
            return null;
        }
        return find(leftPosition).getLetter();
    }

    public static Character right(Character current) {
        int leftPosition = find(current).getNumber() + 1;
        if (outOfBoard(leftPosition)) {
            return null;
        }
        return find(leftPosition).getLetter();
    }

    private static Column find(int position) {
        for (Column column : values()) {
            if (column.getNumber() == position) {
                return column;
            }
        }
        throw new IllegalArgumentException("Couldn't find column with position=" + position);
    }

    private static boolean outOfBoard(int position) {
        return position < 1 || position > 8;
    }

    private static Column find(Character current) {
        for (Column column : values()) {
            if (column.getLetter().equals(current)) {
                return column;
            }
        }
        throw new IllegalArgumentException("Couldn't find column with letter=" + current);
    }
}
