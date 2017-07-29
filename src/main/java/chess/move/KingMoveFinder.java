package chess.move;

import chess.GameState;
import chess.Player;
import chess.Position;
import chess.PositionUtils;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

import static chess.PositionUtils.*;

public class KingMoveFinder implements MoveFinder {

    private final GameState gameState;

    public KingMoveFinder(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public List<Position> findPositionsToMove(Player player, Position position) {
        ArrayList<Position> positions = new ArrayList<>();
        addIfApplicableToMove(positions, up(position));
        addIfApplicableToMove(positions, upAndRight(position));
        addIfApplicableToMove(positions, right(position));
        addIfApplicableToMove(positions, downAndRight(position));
        addIfApplicableToMove(positions, down(position));
        addIfApplicableToMove(positions, downAndLeft(position));
        addIfApplicableToMove(positions, left(position));
        addIfApplicableToMove(positions, upAndLeft(position));
        return positions;
    }

    @Override
    public List<Position> findPositionsToProtect(Player player, Position position) {
        return Lists.newArrayList(up(position),  upAndRight(position), right(position), downAndRight(position),
                down(position), downAndLeft(position), left(position), upAndLeft(position));
    }

    private void addIfApplicableToMove(ArrayList<Position> positions, Position position) {
        if (!gameState.containsMyPiece(position) && gameState.isNotProtected(position)) {
            positions.add(position);
        }
    }
}
