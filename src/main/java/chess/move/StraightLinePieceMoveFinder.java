package chess.move;

import chess.Board;
import chess.Player;
import chess.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class StraightLinePieceMoveFinder implements MoveFinder {

    private Board board;
    private List<Function<Position, Position>> directionFunctions;

    public StraightLinePieceMoveFinder(Board board, List<Function<Position, Position>> directionFunctions) {
        this.board = board;
        this.directionFunctions = directionFunctions;
    }

    @Override
    public List<Position> findPositionsToMove(Player player, Position position) {
        ArrayList<Position> positions = new ArrayList<>();
        for (Function<Position, Position> directionFunction : directionFunctions) {
            positions.addAll(fromDirection(position, directionFunction, player, false));
        }
        return positions;
    }

    @Override
    public List<Position> findPositionsToProtect(Player player, Position position) {
        ArrayList<Position> positions = new ArrayList<>();
        for (Function<Position, Position> directionFunction : directionFunctions) {
            positions.addAll(fromDirection(position, directionFunction, player, true));
        }
        return positions;
    }

    private List<Position> fromDirection(Position position, Function<Position, Position> directionFunction,
                                         Player player, boolean includeMyPiece) {
        List<Position> positions = new ArrayList<>();
        Position destination = directionFunction.apply(position);
        if (destination == null) {
            return positions;
        }
        if (positionShouldBeAdded(destination, player, includeMyPiece)) {
            positions.add(destination);
        }
        if (!board.isNotOccupied(destination)) {
            return positions;
        }
        positions.addAll(fromDirection(destination, directionFunction, player, includeMyPiece));
        return positions;
    }

    private boolean positionShouldBeAdded(Position position, Player player, boolean includeMyPiece) {
        return includeMyPiece || (!board.containsMyPiece(position, player));
    }
}
