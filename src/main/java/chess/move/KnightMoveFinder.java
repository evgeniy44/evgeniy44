package chess.move;

import chess.GameState;
import chess.Player;
import chess.Position;
import chess.PositionUtils;

import java.util.ArrayList;
import java.util.List;

public class KnightMoveFinder implements MoveFinder {
    private GameState gameState;

    public KnightMoveFinder(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public List<Position> findPositionsToMove(Player player, Position position) {
        ArrayList<Position> positions = new ArrayList<>();
        addIfApplicable(positions, PositionUtils.move(position, PositionUtils::up, PositionUtils::up, PositionUtils::right));
        addIfApplicable(positions, PositionUtils.move(position, PositionUtils::up, PositionUtils::up, PositionUtils::left));
        addIfApplicable(positions, PositionUtils.move(position, PositionUtils::up, PositionUtils::left, PositionUtils::left));
        addIfApplicable(positions, PositionUtils.move(position, PositionUtils::down, PositionUtils::left, PositionUtils::left));
        addIfApplicable(positions, PositionUtils.move(position, PositionUtils::down, PositionUtils::down, PositionUtils::left));
        addIfApplicable(positions, PositionUtils.move(position, PositionUtils::down, PositionUtils::down, PositionUtils::right));
        addIfApplicable(positions, PositionUtils.move(position, PositionUtils::down, PositionUtils::right, PositionUtils::right));
        addIfApplicable(positions, PositionUtils.move(position, PositionUtils::up, PositionUtils::right, PositionUtils::right));
        return positions;
    }

    @Override
    public List<Position> findPositionsUnderProtection(Player player, Position piecePosition) {
        return new ArrayList<>();
    }

    private void addIfApplicable(List<Position> positions, Position positionToAdd) {
        if (positionToAdd!= null && canStepOn(positionToAdd)) {
            positions.add(positionToAdd);
        }
    }

    private boolean canStepOn(Position positionToAdd) {
        return gameState.isNotBusy(positionToAdd) || gameState.containsOppositePiece(positionToAdd);
    }
}
