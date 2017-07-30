package chess.move;

import chess.Board;
import chess.PositionUtils;
import chess.pieces.*;
import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.Map;

public class MoveFinderFactory {

    private Map<Class<?>, MoveFinder> moveFinders = new HashMap<>();

    MoveFinderFactory(Board board, Map<Class<?>, MoveFinder> moveFinders) {
        this.moveFinders = moveFinders;
    }

    public MoveFinderFactory(Board board) {
        moveFinders.put(Pawn.class, new PawnMoveFinder(board));
        moveFinders.put(King.class, new KingMoveFinder(board));
        moveFinders.put(Rook.class, new StraightLinePieceMoveFinder(board, Lists.newArrayList(
                PositionUtils::up, PositionUtils::left,
                PositionUtils::down, PositionUtils::right)));
        moveFinders.put(Knight.class, new KnightMoveFinder(board));
        moveFinders.put(Bishop.class, new StraightLinePieceMoveFinder(board, Lists.newArrayList(
                PositionUtils::upAndRight, PositionUtils::upAndLeft,
                PositionUtils::downAndRight, PositionUtils::downAndLeft)));
        moveFinders.put(Queen.class, new StraightLinePieceMoveFinder(board, Lists.newArrayList(
                PositionUtils::upAndRight, PositionUtils::upAndLeft,
                PositionUtils::downAndRight, PositionUtils::downAndLeft,
                PositionUtils::up, PositionUtils::left,
                PositionUtils::down, PositionUtils::right)));
    }

    public MoveFinder getMoveFinder(Class<?> pieceClass) {
        return moveFinders.get(pieceClass);
    }
}
