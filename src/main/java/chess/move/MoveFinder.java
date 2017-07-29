package chess.move;

import chess.Player;
import chess.Position;

import java.util.List;

public interface MoveFinder {

    List<Position> findPositionsToMove(Player player, Position position);

    List<Position> findPositionsToProtect(Player player, Position position);
}
