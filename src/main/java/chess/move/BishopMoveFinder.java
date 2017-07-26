package chess.move;

import chess.GameState;
import chess.Player;
import chess.Position;

import java.util.ArrayList;
import java.util.List;

public class BishopMoveFinder implements MoveFinder {
    private GameState gameState;

    public BishopMoveFinder(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public List<Position> findPositionsToMove(Player player, Position position) {
        ArrayList<Position> positions = new ArrayList<>();
        addIfApplicable(positions, position.up().map(up -> up.upAndLeft()))
        return positions;
    }

    private void addIfApplicable(List<Position> positions, Position positionToAdd) {

    }
}
