package chess.move;

import chess.*;

import java.util.ArrayList;
import java.util.List;

public class KnightMoveFinder implements MoveFinder {
    private Board board;

    public KnightMoveFinder(Board board) {
        this.board = board;
    }

    @Override
    public List<Position> findPositionsToMove(Player player, Position position) {
        ArrayList<Position> positions = new ArrayList<>();
        addIfApplicable(positions,
                PositionUtils.move(position, PositionUtils::up, PositionUtils::up, PositionUtils::right), player);
        addIfApplicable(positions,
                PositionUtils.move(position, PositionUtils::up, PositionUtils::up, PositionUtils::left), player);
        addIfApplicable(positions,
                PositionUtils.move(position, PositionUtils::up, PositionUtils::left, PositionUtils::left), player);
        addIfApplicable(positions,
                PositionUtils.move(position, PositionUtils::down, PositionUtils::left, PositionUtils::left), player);
        addIfApplicable(positions,
                PositionUtils.move(position, PositionUtils::down, PositionUtils::down, PositionUtils::left), player);
        addIfApplicable(positions,
                PositionUtils.move(position, PositionUtils::down, PositionUtils::down, PositionUtils::right), player);
        addIfApplicable(positions,
                PositionUtils.move(position, PositionUtils::down, PositionUtils::right, PositionUtils::right), player);
        addIfApplicable(positions,
                PositionUtils.move(position, PositionUtils::up, PositionUtils::right, PositionUtils::right), player);
        return positions;
    }

    @Override
    public List<Position> findPositionsToProtect(Player player, Position position) {
        return findPositionsToMove(player, position);
    }

    private void addIfApplicable(List<Position> positions, Position positionToAdd, Player player) {
        if (positionToAdd!= null && canStepOn(positionToAdd, player)) {
            positions.add(positionToAdd);
        }
    }

    private boolean canStepOn(Position positionToAdd, Player player) {
        return board.isNotOccupied(positionToAdd) || board.containsOppositePiece(positionToAdd, player);
    }
}
