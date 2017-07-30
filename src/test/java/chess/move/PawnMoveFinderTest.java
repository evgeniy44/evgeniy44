package chess.move;

import chess.Board;
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
    private Board board;

    @InjectMocks
    private PawnMoveFinder pawnMoveFinder = new PawnMoveFinder(board);

    @Test
    public void shouldReturnStepAndJumpMoves() {
        notOppositePositions(board, Player.White, "d3", "f3");
        notBusyPositions(board, "e3", "e4");

        List<Position> movePositions = pawnMoveFinder.findPositionsToMove(Player.White, new Position("e2"));
        assertContainsOnlyPositions(movePositions, "e4", "e3");
    }

    @Test
    public void shouldReturnOnlyStep() {
        notOppositePositions(board, Player.White, "d3", "f3");
        notBusyPositions(board, "e3");
        busyPositions(board, "e4");

        List<Position> movePositions = pawnMoveFinder.findPositionsToMove(Player.White, new Position("e2"));
        assertContainsOnlyPositions(movePositions, "e3");
    }

    @Test
    public void shouldReturnOnlyStepFromNotStartPosition() {
        notOppositePositions(board, Player.White, "d4", "f4");
        notBusyPositions(board, "e4");

        List<Position> movePositions = pawnMoveFinder.findPositionsToMove(Player.White, new Position("e3"));
        assertContainsOnlyPositions(movePositions, "e4");
    }

    @Test
    public void shouldReturnNoPositionsToMove() {
        notOppositePositions(board, Player.White, "d3", "f3");
        notBusyPositions(board, "e4");
        busyPositions(board, "e3");

        Collection<Position> movePositions = pawnMoveFinder.findPositionsToMove(Player.White, new Position("e2"));
        assertThat(movePositions).isEmpty();
    }

    @Test
    public void shouldReturnPositionToHeatLeft() {
        notOppositePositions(board, Player.White, "f3");
        oppositePositions(board, Player.White,"d3");
        notBusyPositions(board, "e4");
        busyPositions(board, "e3");

        List<Position> movePositions = pawnMoveFinder.findPositionsToMove(Player.White, new Position("e2"));
        assertContainsOnlyPositions(movePositions, "d3");
    }

    @Test
    public void shouldReturnPositionToHeatRight() {
        notOppositePositions(board, Player.White, "d3");
        oppositePositions(board, Player.White,"f3");
        notBusyPositions(board, "e4");
        busyPositions(board, "e3");

        List<Position> movePositions = pawnMoveFinder.findPositionsToMove(Player.White, new Position("e2"));
        assertContainsOnlyPositions(movePositions, "f3");
    }

    @Test
    public void shouldReturnAllPossiblePositions() {
        oppositePositions(board, Player.White, "f3", "d3");
        notBusyPositions(board, "e4", "e3");

        List<Position> movePositions = pawnMoveFinder.findPositionsToMove(Player.White, new Position("e2"));
        assertContainsOnlyPositions(movePositions, "f3", "e3", "d3", "e4");
    }

    @Test
    public void shouldReturnAllPosiblePositionsForBlackPlayer() {
        oppositePositions(board, Player.Black, "d6", "f6");
        notBusyPositions(board, "e5", "e6");

        List<Position> movePositions = pawnMoveFinder.findPositionsToMove(Player.Black, new Position("e7"));
        assertContainsOnlyPositions(movePositions, "f6", "e6", "d6", "e5");
    }

    @Test
    public void shouldReturnAllHeatPositionsForBlackPlayer() {
        notOppositePositions(board, Player.White, "d6", "f6");
        notBusyPositions(board, "e5", "e6");

        List<Position> movePositions = pawnMoveFinder.findPositionsToMove(Player.Black, new Position("e7"));
        assertContainsOnlyPositions(movePositions, "e6", "e5");
    }

    @Test
    public void shouldReturnAllProtectedPositions() {
        List<Position> movePositions = pawnMoveFinder.findPositionsToProtect(Player.White, new Position("e4"));
        assertContainsOnlyPositions(movePositions, "d5", "f5");
    }
}