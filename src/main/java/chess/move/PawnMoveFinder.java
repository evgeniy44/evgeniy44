package chess.move;

import chess.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static chess.GameState.BLACK_START_ROW;
import static chess.GameState.WHITE_START_ROW;

public class PawnMoveFinder implements MoveFinder {

    private final Board board;

    public PawnMoveFinder(Board board) {
        this.board = board;
    }

    public List<Position> findPositionsToMove(Player player, Position position) {
        List<Position> positionsToMove = new ArrayList<>();
        positionsToMove.addAll(getPositionsToMove(player, position));
        positionsToMove.addAll(getPositionsToHeat(player, position));
        return positionsToMove;
    }

    @Override
    public List<Position> findPositionsToProtect(Player player, Position position) {
        return getNextHeatPositions(position, player);
    }

    private List<Position> getPositionsToHeat(Player player, Position position) {
        return getNextHeatPositions(position, player).stream()
                .filter(destination -> board.containsOppositePiece(destination, player))
                .collect(Collectors.toList());
    }

    private List<Position> getPositionsToMove(Player player, Position position) {
        List<Position> positions = new ArrayList<>();
        if (canJump(position, player)) {
            positions.add(step(position, player));
            positions.add(step(step(position, player), player));
        } else if (canStep(position, player)) {
            positions.add(step(position, player));
        }
        return positions;
    }

    private boolean canStep(Position position, Player player) {
        return board.isNotOccupied(step(position, player));
    }

    private Position step(Position position, Player player) {
        if (Player.White.equals(player)) {
            return PositionUtils.up(position);
        }
        return PositionUtils.down(position);
    }

    private boolean canJump(Position position, Player player) {
        return isStartPosition(position.getRow(), player) && board.isNotOccupied(step(position, player))
                && board.isNotOccupied(step(step(position, player), player));
    }

    private boolean isStartPosition(int row, Player player) {
        if (Player.White.equals(player)) {
            return row == WHITE_START_ROW;
        }
        return row == BLACK_START_ROW;
    }

    public List<Position> getNextHeatPositions(Position position, Player player) {
        List heatPositions = new ArrayList();
        if (Player.White.equals(player)) {
            heatPositions.add(PositionUtils.upAndLeft(position));
            heatPositions.add(PositionUtils.upAndRight(position));
        } else {
            heatPositions.add(PositionUtils.downAndLeft(position));
            heatPositions.add(PositionUtils.downAndRight(position));
        }
        return heatPositions;
    }
}
