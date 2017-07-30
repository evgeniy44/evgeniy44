package chess.move;

import chess.Board;
import chess.Player;
import chess.Position;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public abstract class BaseMoveFinderTest {

    protected void notBusyPositions(Board board, String... positions) {
        for (String position : positions) {
            when(board.isNotOccupied(new Position(position))).thenReturn(true);
        }
    }

    protected void busyPositions(Board board, String... positions) {
        for (String position : positions) {
            when(board.isNotOccupied(new Position(position))).thenReturn(false);
        }
    }

    protected void oppositePositions(Board board, Player player, String... positions) {
        for (String position : positions) {
            when(board.containsOppositePiece(new Position(position), player)).thenReturn(true);
        }
    }

    protected void myPositions(Board board, Player player, String... positions) {
        for (String position : positions) {
            when(board.containsMyPiece(new Position(position), player)).thenReturn(true);
        }
    }

    protected void notMyPositions(Board board, Player player, String... positions) {
        for (String position : positions) {
            when(board.containsMyPiece(new Position(position), player)).thenReturn(false);
        }
    }

    protected void notOppositePositions(Board board, Player player, String... positions) {
        for (String position : positions) {
            when(board.containsOppositePiece(new Position(position), player)).thenReturn(false);
        }
    }

    protected void assertContainsOnlyPositions(List<Position> actualPositions,
                                               String... expectedPositions) {
        List<Position> positions =
                Arrays.asList(expectedPositions).stream().map(Position::new).collect(Collectors.toList());
        assertThat(actualPositions).containsOnly(positions.toArray(new Position[actualPositions.size()]));
    }
}
