package chess.move;

import chess.GameState;
import chess.Player;
import chess.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class StraightLinePieceMoveFinder implements MoveFinder {

    private GameState gameState;
    private List<Function<Position, Position>> directionFunctions;

    public StraightLinePieceMoveFinder(GameState gameState, List<Function<Position, Position>> directionFunctions) {
        this.gameState = gameState;
        this.directionFunctions = directionFunctions;
    }

    @Override
    public List<Position> findPositionsToMove(Player player, Position position) {
        ArrayList<Position> positions = new ArrayList<>();
        for (Function<Position, Position> directionFunction : directionFunctions) {
            positions.addAll(fromDirection(position, directionFunction, false));
        }
        return positions;
    }

    @Override
    public List<Position> findPositionsUnderProtection(Player player, Position position) {
        ArrayList<Position> positions = new ArrayList<>();
        for (Function<Position, Position> directionFunction : directionFunctions) {
            positions.addAll(fromDirection(position, directionFunction, true));
        }
        return positions;
    }

    private List<Position> fromDirection(Position position, Function<Position, Position> directionFunction,
                                         boolean includeMyPiece) {
        List<Position> positions = new ArrayList<>();
        Position destination = directionFunction.apply(position);
        if (destination == null) {
            return positions;
        }
        if (positionShouldBeAdded(destination, includeMyPiece)) {
            positions.add(destination);
        }
        if (!gameState.isNotBusy(destination)) {
            return positions;
        }
        positions.addAll(fromDirection(destination, directionFunction, includeMyPiece));
        return positions;
    }

    private boolean positionShouldBeAdded(Position position, boolean includeMyPiece) {
        return includeMyPiece || (!gameState.containsMyPiece(position));
    }
}
