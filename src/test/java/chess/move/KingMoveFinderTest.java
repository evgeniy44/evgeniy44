package chess.move;

import chess.GameState;
import chess.Player;
import chess.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class KingMoveFinderTest extends BaseMoveFinderTest {

    @Mock
    private GameState gameState;

    @InjectMocks
    private KingMoveFinder moveFinder = new KingMoveFinder(gameState);

    @Test
    public void shouldReturnAllPositionsToMove() {
        notMyPositions(gameState, "d3", "d4", "d5", "e5", "f5", "f4", "f3", "e3");
        notProtected(gameState, "d3", "d4", "d5", "e5", "f5", "f4", "f3", "e3");

        List<Position> positions = moveFinder.findPositionsToMove(Player.White, new Position("e4"));
        assertContainsOnlyPositions(positions, "d3", "d4", "d5", "e5", "f5", "f4", "f3", "e3");
    }

    @Test
    public void shouldReturnAllPositionsToMovProtect() {
        List<Position> positions = moveFinder.findPositionsToProtect(Player.White, new Position("e4"));
        assertContainsOnlyPositions(positions, "d3", "d4", "d5", "e5", "f5", "f4", "f3", "e3");
    }

    @Test
    public void shouldReturnAllPositionsExceptProtectedFields() {
        notMyPositions(gameState, "d3", "d4", "d5", "e5", "f5", "f4", "f3", "e3");
        notProtected(gameState, "d3", "d4", "d5", "e5", "f5", "f4");
        isProtected(gameState, "f3", "e3");

        List<Position> positions = moveFinder.findPositionsToMove(Player.White, new Position("e4"));
        assertContainsOnlyPositions(positions, "d3", "d4", "d5", "e5", "f5", "f4");
    }

    @Test
    public void shouldReturnAllPositionsExceptProtectedFieldsAndMyPieces() {
        notMyPositions(gameState, "d5", "e5", "f5", "f4", "f3", "e3");
        myPositions(gameState, "d3", "d4");
        notProtected(gameState, "d3", "d4", "d5", "e5", "f5", "f4");
        isProtected(gameState, "f3", "e3");

        List<Position> positions = moveFinder.findPositionsToMove(Player.White, new Position("e4"));
        assertContainsOnlyPositions(positions, "d5", "e5", "f5", "f4");
    }
}