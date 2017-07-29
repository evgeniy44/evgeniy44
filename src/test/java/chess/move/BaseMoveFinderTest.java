package chess.move;

import chess.GameState;
import chess.Position;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public abstract class BaseMoveFinderTest {

    protected void notBusyPositions(GameState gameState, String... positions) {
        for (String position : positions) {
            when(gameState.isNotBusy(new Position(position))).thenReturn(true);
        }
    }

    protected void busyPositions(GameState gameState, String... positions) {
        for (String position : positions) {
            when(gameState.isNotBusy(new Position(position))).thenReturn(false);
        }
    }

    protected void oppositePositions(GameState gameState, String... positions) {
        for (String position : positions) {
            when(gameState.containsOppositePiece(new Position(position))).thenReturn(true);
        }
    }

    protected void myPositions(GameState gameState, String... positions) {
        for (String position : positions) {
            when(gameState.containsMyPiece(new Position(position))).thenReturn(true);
        }
    }

    protected void notMyPositions(GameState gameState, String... positions) {
        for (String position : positions) {
            when(gameState.containsMyPiece(new Position(position))).thenReturn(false);
        }
    }

    protected void notProtected(GameState gameState, String... positions) {
        for (String position : positions) {
            when(gameState.isNotProtected(new Position(position))).thenReturn(true);
        }
    }

    protected void isProtected(GameState gameState, String... positions) {
        for (String position : positions) {
            when(gameState.isNotProtected(new Position(position))).thenReturn(false);
        }
    }


    protected void notOppositePositions(GameState gameState, String... positions) {
        for (String position : positions) {
            when(gameState.containsOppositePiece(new Position(position))).thenReturn(false);
        }
    }

    protected void assertContainsOnlyPositions(List<Position> actualPositions,
                                               String... expectedPositions) {
        List<Position> positions =
                Arrays.asList(expectedPositions).stream().map(Position::new).collect(Collectors.toList());
        assertThat(actualPositions).containsOnly(positions.toArray(new Position[actualPositions.size()]));
    }
}
