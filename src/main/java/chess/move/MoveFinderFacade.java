package chess.move;

import chess.GameState;
import chess.Player;
import chess.Position;
import chess.PositionUtils;
import chess.pieces.*;
import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoveFinderFacade {

    private final GameState gameState;
    private Map<Class<?>, MoveFinder> moveFinders = new HashMap<>();

    /**
     * Only for tests.
     */
    MoveFinderFacade(GameState gameState, Map<Class<?>, MoveFinder> moveFinders) {
        this.gameState = gameState;
        this.moveFinders = moveFinders;
    }

    public MoveFinderFacade(GameState gameState) {
        this.gameState = gameState;

        moveFinders.put(Pawn.class, new PawnMoveFinder(gameState));
        moveFinders.put(King.class, new KingMoveFinder(gameState));
        moveFinders.put(Rook.class, new StraightLinePieceMoveFinder(gameState, Lists.newArrayList(
                PositionUtils::up, PositionUtils::left,
                PositionUtils::down, PositionUtils::right)));
        moveFinders.put(Knight.class, new KnightMoveFinder(gameState));
        moveFinders.put(Bishop.class, new StraightLinePieceMoveFinder(gameState, Lists.newArrayList(
                PositionUtils::upAndRight, PositionUtils::upAndLeft,
                PositionUtils::downAndRight, PositionUtils::downAndLeft)));
        moveFinders.put(Queen.class, new StraightLinePieceMoveFinder(gameState, Lists.newArrayList(
                PositionUtils::upAndRight, PositionUtils::upAndLeft,
                PositionUtils::downAndRight, PositionUtils::downAndLeft,
                PositionUtils::up, PositionUtils::left,
                PositionUtils::down, PositionUtils::right)));
    }

    public List<Position> findPositionsToMove(Class pieceClass, Player player, Position position) {
        return moveFinders.get(pieceClass).findPositionsToMove(player, position);
    }

    public List<Position> findPositionsToProtect(Class pieceClass, Player player, Position position) {
        return moveFinders.get(pieceClass).findPositionsToProtect(player, position);
    }
}
