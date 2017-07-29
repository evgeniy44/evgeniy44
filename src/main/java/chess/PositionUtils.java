package chess;

import java.util.function.Function;

public class PositionUtils {


    public static Position up(Position position) {
        Position destinationPosition = new Position(position.getColumn(), position.getRow() + 1);
        if (destinationPosition.outOfBoard()) {
            return null;
        }
        return destinationPosition;
    }

    public static Position down(Position position) {
        Position destinationPosition = new Position(position.getColumn(), position.getRow() - 1);
        if (destinationPosition.outOfBoard()) {
            return null;
        }
        return destinationPosition;
    }

    public static Position left(Position position) {
        Character leftColumn = Column.left(position.getColumn());
        if (leftColumn != null) {
            return new Position(leftColumn, position.getRow());
        }
        return null;
    }

    public static Position move(Position position, Function<Position, Position>... moveFunctions) {
        Position intermediatePosition = position;
        for (Function<Position, Position> moveFunction : moveFunctions) {
            intermediatePosition = moveFunction.apply(intermediatePosition);
            if (intermediatePosition == null) {
                return null;
            }
        }
        return intermediatePosition;
    }

    public static Position right(Position position) {
        Character rightColumn = Column.right(position.getColumn());
        if (rightColumn != null) {
            return new Position(rightColumn, position.getRow());
        }
        return null;
    }

    public static Position upAndLeft(Position position) {
        return move(position, PositionUtils::up, PositionUtils::left);
    }

    public static Position upAndRight(Position position) {
        return move(position, PositionUtils::up, PositionUtils::right);
    }

    public static Position downAndRight(Position position) {
        return move(position, PositionUtils::down, PositionUtils::right);
    }

    public static Position downAndLeft(Position position) {
        return move(position, PositionUtils::down, PositionUtils::left);
    }
}
