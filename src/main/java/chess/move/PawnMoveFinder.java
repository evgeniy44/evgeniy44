package chess.move;

import chess.GameState;
import chess.Player;
import chess.Position;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static chess.GameState.BLACK_START_ROW;
import static chess.GameState.WHITE_START_ROW;

public class PawnMoveFinder implements MoveFinder {

    private final GameState gameState;

    public PawnMoveFinder(GameState gameState) {
        this.gameState = gameState;
    }

    public List<Position> findPositionsToMove(Player player, Position position) {
        List<Position> positionsToMove = new ArrayList<>();
        positionsToMove.addAll(getPositionsToMove(player, position));
        positionsToMove.addAll(getPositionsToHeat(player, position));
        return positionsToMove;
    }

    private List<Position> getPositionsToHeat(Player player, Position position) {
        return getNextHeatPositions(position, player).stream().filter(gameState::containsOppositePiece)
                .collect(Collectors.toList());
    }

    private List<Position> getPositionsToMove(Player player, Position position) {
        List<Position> positions = new ArrayList<>();
        if (canJump(position, player)) {
            positions.add(step(position, player));
            positions.add(step(step(position, player), player));
        } else if (canStep(position, player)) {
            positions.add(position.up());
        }
        return positions;
    }

    private boolean canStep(Position position, Player player) {
        return isStartPosition(position.getRow(), player) && gameState.isNotBusy(position.up());
    }

    private Position step(Position position, Player player) {
        if (Player.White.equals(player)) {
            return position.up();
        }
        return position.down();
    }

    private boolean canJump(Position position, Player player) {
        return isStartPosition(position.getRow(), player) && gameState.isNotBusy(position.up())
                && gameState.isNotBusy(position.up().up());
    }

    private boolean isStartPosition(int row, Player player) {
        if (Player.White.equals(player)) {
            return row == WHITE_START_ROW;
        }
        return row == BLACK_START_ROW;
    }

    public Collection<Position> getNextHeatPositions(Position position, Player player) {
        List heatPositions = new ArrayList();
        if (Player.White.equals(player)) {
            heatPositions.add(position.upAndLeft());
            heatPositions.add(position.upAndRight());
        } else {
            heatPositions.add(position.downAndLeft());
            heatPositions.add(position.downAndRight());
        }
        return heatPositions;
    }
}
