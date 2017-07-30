package chess.move;

import chess.Board;
import chess.Player;
import chess.Position;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

import static chess.PositionUtils.*;

public class KingMoveFinder implements MoveFinder {

    private final Board board;

    public KingMoveFinder(Board board) {
        this.board = board;
    }

    @Override
    public List<Position> findPositionsToMove(Player player, Position position) {
        ArrayList<Position> positions = new ArrayList<>();
        addIfApplicableToMove(positions, up(position), player);
        addIfApplicableToMove(positions, upAndRight(position), player);
        addIfApplicableToMove(positions, right(position), player);
        addIfApplicableToMove(positions, downAndRight(position), player);
        addIfApplicableToMove(positions, down(position), player);
        addIfApplicableToMove(positions, downAndLeft(position), player);
        addIfApplicableToMove(positions, left(position), player);
        addIfApplicableToMove(positions, upAndLeft(position), player);
        return positions;
    }

    @Override
    public List<Position> findPositionsToProtect(Player player, Position position) {
        return Lists.newArrayList(up(position),  upAndRight(position), right(position), downAndRight(position),
                down(position), downAndLeft(position), left(position), upAndLeft(position));
    }

    private void addIfApplicableToMove(ArrayList<Position> positions, Position position, Player player) {
        if (position != null
                && !board.containsMyPiece(position, player)) {
            positions.add(position);
        }
    }
}
