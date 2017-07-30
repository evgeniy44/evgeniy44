package chess.move;

import chess.Board;
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
public class KnightMoveFinderTest extends BaseMoveFinderTest {

    @Mock
    private Board board;

    @InjectMocks
    private KnightMoveFinder knightMoveFinder = new KnightMoveFinder(board);

    @Test
    public void shouldReturnAll8AvailablePositionIfTheyAreNotBusy() {
        notBusyPositions(board, "d2", "f2", "g3", "g5", "f6", "d6", "c5", "c3");
        List<Position> moves = knightMoveFinder.findPositionsToMove(Player.White, new Position("e4"));
        assertContainsOnlyPositions(moves,"d2", "f2", "g3", "g5", "f6", "d6", "c5", "c3");
    }

    @Test
    public void shouldReturnAll8UnderProtections() {
        notBusyPositions(board, "d2", "f2", "g3", "g5", "f6", "d6", "c5", "c3");
        List<Position> moves = knightMoveFinder.findPositionsToProtect(Player.White, new Position("e4"));
        assertContainsOnlyPositions(moves,"d2", "f2", "g3", "g5", "f6", "d6", "c5", "c3");
    }


    @Test
    public void shouldReturn7AvailablePositionWhenOneOfThemIsBusy() {
        notBusyPositions(board, "d2", "f2", "g3", "g5", "f6", "d6", "c3");
        busyPositions(board, "c5");
        oppositePositions(board, Player.White, "c5");

        List<Position> moves = knightMoveFinder.findPositionsToMove(Player.White, new Position("e4"));
        assertContainsOnlyPositions(moves,"d2", "f2", "g3", "g5", "f6", "d6", "c3", "c5");
    }

    @Test
    public void shouldReturn7AvailablePositionWhenOneOfThemIsBusyUnderProtection() {
        notBusyPositions(board, "d2", "f2", "g3", "g5", "f6", "d6", "c3");
        busyPositions(board, "c5");
        oppositePositions(board, Player.White, "c5");

        List<Position> moves = knightMoveFinder.findPositionsToProtect(Player.White, new Position("e4"));
        assertContainsOnlyPositions(moves,"d2", "f2", "g3", "g5", "f6", "d6", "c3", "c5");
    }

    @Test
    public void shouldReturn7AvailablePositionyWhenOneOfThemIsBusyButNotHeatable() {
        notBusyPositions(board, "d2", "f2", "g3", "g5", "f6", "d6", "c3");
        busyPositions(board, "c5");
        oppositePositions(board, Player.White, "g5");
        notOppositePositions(board, Player.White, "c5");

        List<Position> moves = knightMoveFinder.findPositionsToMove(Player.White, new Position("e4"));
        assertContainsOnlyPositions(moves, "d2", "f2", "g3", "g5", "f6", "d6", "c3");
    }

    @Test
    public void shouldReturn7AvailablePositionyWhenOneOfThemIsBusyButNotHeatableUnderProtection() {
        notBusyPositions(board, "d2", "f2", "g3", "g5", "f6", "d6", "c3");
        busyPositions(board, "c5");
        oppositePositions(board, Player.White, "g5");
        notOppositePositions(board, Player.White, "c5");

        List<Position> moves = knightMoveFinder.findPositionsToProtect(Player.White, new Position("e4"));
        assertContainsOnlyPositions(moves, "d2", "f2", "g3", "g5", "f6", "d6", "c3");
    }

    @Test
    public void shouldNotReturnPositionsWhenTheyAreOutOfTheBoard() {
        notBusyPositions(board, "b3", "c2");

        List<Position> moves = knightMoveFinder.findPositionsToMove(Player.White, new Position("a1"));
        assertContainsOnlyPositions(moves, "b3", "c2");
    }

    @Test
    public void shouldNotReturnPositionsUnderProtectionWhenTheyAreOutOfTheBoard() {
        notBusyPositions(board, "b3", "c2");

        List<Position> moves = knightMoveFinder.findPositionsToProtect(Player.White, new Position("a1"));
        assertContainsOnlyPositions(moves, "b3", "c2");
    }
}
