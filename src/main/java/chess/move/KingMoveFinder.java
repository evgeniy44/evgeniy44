package chess.move;

import chess.GameState;
import chess.Player;
import chess.Position;

import java.util.ArrayList;
import java.util.List;

public class KingMoveFinder implements MoveFinder {

    private final GameState gameState;

    public KingMoveFinder(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public List<Position> findPositionsToMove(Player player, Position position) {
        return new ArrayList<>();
    }

    @Override
    public List<Position> findPositionsUnderProtection(Player player, Position piecePosition) {
        return new ArrayList<>();
    }
}
