package chess.move;

import chess.GameState;
import chess.Player;
import chess.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class PawnMoveFinderTest extends BaseMoveFinderTest {

    @Mock
    private GameState gameState;

    @InjectMocks
    private PawnMoveFinder pawnMoveFinder = new PawnMoveFinder(gameState);

    @Test
    public void shouldReturnStepAndJumpMoves() {
        notOppositePositions(gameState, "d3", "f3");
        notBusyPositions(gameState, "e3", "e4");

        List<Position> movePositions = pawnMoveFinder.findPositionsToMove(Player.White, new Position("e2"));
        assertContainsOnlyPositions(movePositions, "e4", "e3");
    }

    @Test
    public void shouldReturnOnlyStep() {
        notOppositePositions(gameState, "d3", "f3");
        notBusyPositions(gameState, "e3");
        busyPositions(gameState, "e4");

        List<Position> movePositions = pawnMoveFinder.findPositionsToMove(Player.White, new Position("e2"));
        assertContainsOnlyPositions(movePositions, "e3");
    }

    @Test
    public void shouldReturnNoPositionsToMove() {
        notOppositePositions(gameState, "d3", "f3");
        notBusyPositions(gameState, "e4");
        busyPositions(gameState, "e3");

        Collection<Position> movePositions = pawnMoveFinder.findPositionsToMove(Player.White, new Position("e2"));
        assertThat(movePositions).isEmpty();
    }

    @Test
    public void shouldReturnPositionToHeatLeft() {
        notOppositePositions(gameState, "f3");
        oppositePositions(gameState, "d3");
        notBusyPositions(gameState, "e4");
        busyPositions(gameState, "e3");

        List<Position> movePositions = pawnMoveFinder.findPositionsToMove(Player.White, new Position("e2"));
        assertContainsOnlyPositions(movePositions, "d3");
    }

    @Test
    public void shouldReturnPositionToHeatRight() {
        notOppositePositions(gameState, "d3");
        oppositePositions(gameState, "f3");
        notBusyPositions(gameState, "e4");
        busyPositions(gameState, "e3");

        List<Position> movePositions = pawnMoveFinder.findPositionsToMove(Player.White, new Position("e2"));
        assertContainsOnlyPositions(movePositions, "f3");
    }

    @Test
    public void shouldReturnAllPossiblePositions() {
        oppositePositions(gameState, "f3", "d3");
        notBusyPositions(gameState, "e4", "e3");

        List<Position> movePositions = pawnMoveFinder.findPositionsToMove(Player.White, new Position("e2"));
        assertContainsOnlyPositions(movePositions, "f3", "e3", "d3", "e4");
    }

    @Test
    public void shouldReturnAllPosiblePositionsForBlackPlayer() {
        oppositePositions(gameState, "d6", "f6");
        notBusyPositions(gameState, "e5", "e6");

        List<Position> movePositions = pawnMoveFinder.findPositionsToMove(Player.Black, new Position("e7"));
        assertContainsOnlyPositions(movePositions, "f6", "e6", "d6", "e5");
    }

    @Test
    public void shouldReturnAllHeatPositionsForBlackPlayer() {
        notOppositePositions(gameState, "d6", "f6");
        notBusyPositions(gameState, "e5", "e6");

        List<Position> movePositions = pawnMoveFinder.findPositionsToMove(Player.Black, new Position("e7"));
        assertContainsOnlyPositions(movePositions, "e6", "e5");
    }

    @Test
    public void shouldReturnAllProtectedPositions() {
        List<Position> movePositions = pawnMoveFinder.findPositionsToProtect(Player.White, new Position("e4"));
        assertContainsOnlyPositions(movePositions, "d5", "f5");
    }
}