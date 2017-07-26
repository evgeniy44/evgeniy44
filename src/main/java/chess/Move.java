package chess;

public class Move {

    private final Position sourcePosition;
    private final Position targetPosition;

    public Move(Position sourcePosition, Position targetPosition) {
        this.sourcePosition = sourcePosition;
        this.targetPosition = targetPosition;
    }

    public Position getSourcePosition() {
        return sourcePosition;
    }

    public Position getTargetPosition() {
        return targetPosition;
    }

    @Override
    public String toString() {
        return sourcePosition + " " + targetPosition;
    }
}
