package chess;

public class Move {

    private final Position sourcePosition;
    private final Position targetPosition;

    public Move(String sourcePosition, String targetPosition) {
        this.sourcePosition = new Position(sourcePosition);
        this.targetPosition = new Position(targetPosition);
    }

    public Move(Position sourcePosition, Position targetPosition) {
        this.sourcePosition = sourcePosition;
        this.targetPosition = targetPosition;
    }

    public Position from() {
        return sourcePosition;
    }

    public Position to() {
        return targetPosition;
    }

    @Override
    public String toString() {
        return sourcePosition + " " + targetPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Move move = (Move) o;

        if (!sourcePosition.equals(move.sourcePosition)) return false;
        return targetPosition.equals(move.targetPosition);
    }

    @Override
    public int hashCode() {
        int result = sourcePosition.hashCode();
        result = 31 * result + targetPosition.hashCode();
        return result;
    }
}
