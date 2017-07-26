package chess.move;

import chess.GameState;
import chess.Player;
import chess.Position;

import java.util.ArrayList;
import java.util.List;

public class RookMoveFinder implements MoveFinder {
    private GameState gameState;

    public RookMoveFinder(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public List<Position> findPositionsToMove(Player player, Position position) {
        return new ArrayList<>();
    }
}
