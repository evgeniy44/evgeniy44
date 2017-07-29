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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

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
    public void shouldReturn7AvailablePositionWhenOneOfThemIsBusy() {
        notBusyPositions(gameState, "d2", "f2", "g3", "g5", "f6", "d6", "c3");
        busyPositions(gameState, "c5");
        containsOppositePiece(gameState, "c5");

        List<Position> moves = knightMoveFinder.findPositionsToMove(Player.White, new Position("e4"));
        assertContainsOnlyPositions(moves,"d2", "f2", "g3", "g5", "f6", "d6", "c3", "c5");
    }

    @Test
    public void shouldReturn7AvailablePositionyWhenOneOfThemIsBusyButHeatable() {
        when(gameState.isNotBusy(new Position("d2"))).thenReturn(true);
        when(gameState.isNotBusy(new Position("f2"))).thenReturn(true);
        when(gameState.isNotBusy(new Position("g3"))).thenReturn(true);
        when(gameState.isNotBusy(new Position("g5"))).thenReturn(true);
        when(gameState.containsOppositePiece(new Position("g5"))).thenReturn(true);
        when(gameState.isNotBusy(new Position("f6"))).thenReturn(true);
        when(gameState.isNotBusy(new Position("d6"))).thenReturn(true);
        when(gameState.isNotBusy(new Position("c5"))).thenReturn(false);
        when(gameState.containsOppositePiece(new Position("c5"))).thenReturn(false);
        when(gameState.isNotBusy(new Position("c3"))).thenReturn(true);

        List<Position> moves = knightMoveFinder.findPositionsToMove(Player.White, new Position("e4"));

        assertThat(moves).containsOnly(new Position("d2"), new Position("f2"),
                new Position("g3"), new Position("g5"), new Position("f6"),
                new Position("d6"), new Position("c3"));
    }

    @Test
    public void shouldNotReturnPositionsWhenTheyAreOutOfTheBoard() {
        when(gameState.isNotBusy(new Position("b3"))).thenReturn(true);
        when(gameState.isNotBusy(new Position("c2"))).thenReturn(true);

        List<Position> moves = knightMoveFinder.findPositionsToMove(Player.White, new Position("a1"));

        assertThat(moves).containsOnly(new Position("b3"), new Position("c2"));
    }
}
