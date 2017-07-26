package chess.move;

import chess.GameState;
import chess.Player;
import chess.Position;

import java.util.ArrayList;
import java.util.List;

public class QueenMoveFinder implements MoveFinder {
    public QueenMoveFinder(GameState gameState) {
    }

    @Override
    public List<Position> findPositionsToMove(Player player, Position position) {
        return new ArrayList<>();
    }
}
