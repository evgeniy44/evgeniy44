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
public class KnightMoveFinderTest extends BaseMoveFinderTest {

    @Mock
    private GameState gameState;

    @InjectMocks
    private KnightMoveFinder knightMoveFinder = new KnightMoveFinder(gameState);

    @Test
    public void shouldReturnAll8AvailablePositionIfTheyAreNotBusy() {
        notBusyPositions(gameState, "d2", "f2", "g3", "g5", "f6", "d6", "c5", "c3");
        List<Position> moves = knightMoveFinder.findPositionsToMove(Player.White, new Position("e4"));
        assertContainsOnlyPositions(moves,"d2", "f2", "g3", "g5", "f6", "d6", "c5", "c3");
    }

    @Test
    public void shouldReturnAll8UnderProtections() {
        notBusyPositions(gameState, "d2", "f2", "g3", "g5", "f6", "d6", "c5", "c3");
        List<Position> moves = knightMoveFinder.findPositionsUnderProtection(Player.White, new Position("e4"));
        assertContainsOnlyPositions(moves,"d2", "f2", "g3", "g5", "f6", "d6", "c5", "c3");
    }


    @Test
    public void shouldReturn7AvailablePositionWhenOneOfThemIsBusy() {
        notBusyPositions(gameState, "d2", "f2", "g3", "g5", "f6", "d6", "c3");
        busyPositions(gameState, "c5");
        oppositePositions(gameState, "c5");

        List<Position> moves = knightMoveFinder.findPositionsToMove(Player.White, new Position("e4"));
        assertContainsOnlyPositions(moves,"d2", "f2", "g3", "g5", "f6", "d6", "c3", "c5");
    }

    @Test
    public void shouldReturn7AvailablePositionWhenOneOfThemIsBusyUnderProtection() {
        notBusyPositions(gameState, "d2", "f2", "g3", "g5", "f6", "d6", "c3");
        busyPositions(gameState, "c5");
        oppositePositions(gameState, "c5");

        List<Position> moves = knightMoveFinder.findPositionsUnderProtection(Player.White, new Position("e4"));
        assertContainsOnlyPositions(moves,"d2", "f2", "g3", "g5", "f6", "d6", "c3", "c5");
    }

    @Test
    public void shouldReturn7AvailablePositionyWhenOneOfThemIsBusyButNotHeatable() {
        notBusyPositions(gameState, "d2", "f2", "g3", "g5", "f6", "d6", "c3");
        busyPositions(gameState, "c5");
        oppositePositions(gameState, "g5");
        notOppositePositions(gameState, "c5");

        List<Position> moves = knightMoveFinder.findPositionsToMove(Player.White, new Position("e4"));
        assertContainsOnlyPositions(moves, "d2", "f2", "g3", "g5", "f6", "d6", "c3");
    }

    @Test
    public void shouldReturn7AvailablePositionyWhenOneOfThemIsBusyButNotHeatableUnderProtection() {
        notBusyPositions(gameState, "d2", "f2", "g3", "g5", "f6", "d6", "c3");
        busyPositions(gameState, "c5");
        oppositePositions(gameState, "g5");
        notOppositePositions(gameState, "c5");

        List<Position> moves = knightMoveFinder.findPositionsUnderProtection(Player.White, new Position("e4"));
        assertContainsOnlyPositions(moves, "d2", "f2", "g3", "g5", "f6", "d6", "c3");
    }

    @Test
    public void shouldNotReturnPositionsWhenTheyAreOutOfTheBoard() {
        notBusyPositions(gameState, "b3", "c2");

        List<Position> moves = knightMoveFinder.findPositionsToMove(Player.White, new Position("a1"));
        assertContainsOnlyPositions(moves, "b3", "c2");
    }

    @Test
    public void shouldNotReturnPositionsUnderProtectionWhenTheyAreOutOfTheBoard() {
        notBusyPositions(gameState, "b3", "c2");

        List<Position> moves = knightMoveFinder.findPositionsUnderProtection(Player.White, new Position("a1"));
        assertContainsOnlyPositions(moves, "b3", "c2");
    }
}
