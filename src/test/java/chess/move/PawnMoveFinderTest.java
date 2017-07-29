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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class PawnMoveFinderTest {

    @Mock
    private GameState gameState;

    @InjectMocks
    private PawnMoveFinder pawnMoveFinder = new PawnMoveFinder(gameState);

    @Test
    public void shouldReturnStepAndJumpMoves() {
        when(gameState.containsOppositePiece(new Position("d3"))).thenReturn(false);
        when(gameState.containsOppositePiece(new Position("f3"))).thenReturn(false);
        when(gameState.isNotBusy(new Position("e3"))).thenReturn(true);
        when(gameState.isNotBusy(new Position("e4"))).thenReturn(true);

        Collection<Position> movePositions = pawnMoveFinder.findPositionsToMove(Player.White, new Position("e2"));
        assertThat(movePositions).containsOnly(new Position("e4"), new Position("e3"));
    }

    @Test
    public void shouldReturnOnlyStep() {
        when(gameState.containsOppositePiece(new Position("d3"))).thenReturn(false);
        when(gameState.containsOppositePiece(new Position("f3"))).thenReturn(false);
        when(gameState.isNotBusy(new Position("e3"))).thenReturn(true);
        when(gameState.isNotBusy(new Position("e4"))).thenReturn(false);

        Collection<Position> movePositions = pawnMoveFinder.findPositionsToMove(Player.White, new Position("e2"));
        assertThat(movePositions).containsOnly(new Position("e3"));
    }

    @Test
    public void shouldReturnNoPositionsToMove() {
        when(gameState.containsOppositePiece(new Position("d3"))).thenReturn(false);
        when(gameState.containsOppositePiece(new Position("f3"))).thenReturn(false);
        when(gameState.isNotBusy(new Position("e3"))).thenReturn(false);
        when(gameState.isNotBusy(new Position("e4"))).thenReturn(true);

        Collection<Position> movePositions = pawnMoveFinder.findPositionsToMove(Player.White, new Position("e2"));
        assertThat(movePositions).isEmpty();
    }

    @Test
    public void shouldReturnPositionToHeatLeft() {
        when(gameState.containsOppositePiece(new Position("d3"))).thenReturn(true);
        when(gameState.containsOppositePiece(new Position("f3"))).thenReturn(false);
        when(gameState.isNotBusy(new Position("e3"))).thenReturn(false);
        when(gameState.isNotBusy(new Position("e4"))).thenReturn(true);

        Collection<Position> movePositions = pawnMoveFinder.findPositionsToMove(Player.White, new Position("e2"));
        assertThat(movePositions).containsOnly(new Position("d3"));
    }

    @Test
    public void shouldReturnPositionToHeatRight() {
        when(gameState.containsOppositePiece(new Position("d3"))).thenReturn(false);
        when(gameState.containsOppositePiece(new Position("f3"))).thenReturn(true);
        when(gameState.isNotBusy(new Position("e3"))).thenReturn(false);
        when(gameState.isNotBusy(new Position("e4"))).thenReturn(true);

        Collection<Position> movePositions = pawnMoveFinder.findPositionsToMove(Player.White, new Position("e2"));
        assertThat(movePositions).containsOnly(new Position("f3"));
    }

    @Test
    public void shouldReturnAllPosiblePositions() {
        when(gameState.containsOppositePiece(new Position("d3"))).thenReturn(true);
        when(gameState.containsOppositePiece(new Position("f3"))).thenReturn(true);
        when(gameState.isNotBusy(new Position("e3"))).thenReturn(true);
        when(gameState.isNotBusy(new Position("e4"))).thenReturn(true);

        Collection<Position> movePositions = pawnMoveFinder.findPositionsToMove(Player.White, new Position("e2"));
        assertThat(movePositions).containsOnly(new Position("f3"),
                new Position("e3"),
                new Position("d3"),
                new Position("e4"));
    }

    @Test
    public void shouldReturnAllPosiblePositionsForBlackPlayer() {
        when(gameState.containsOppositePiece(new Position("d6"))).thenReturn(true);
        when(gameState.containsOppositePiece(new Position("f6"))).thenReturn(true);
        when(gameState.isNotBusy(new Position("e6"))).thenReturn(true);
        when(gameState.isNotBusy(new Position("e5"))).thenReturn(true);

        Collection<Position> movePositions = pawnMoveFinder.findPositionsToMove(Player.Black, new Position("e7"));
        assertThat(movePositions).containsOnly(new Position("f6"),
                new Position("e6"),
                new Position("d6"),
                new Position("e5"));
    }

    @Test
    public void shouldReturnAllHeatPositionsForBlackPlayer() {
        when(gameState.containsOppositePiece(new Position("d6"))).thenReturn(false);
        when(gameState.containsOppositePiece(new Position("f6"))).thenReturn(false);
        when(gameState.isNotBusy(new Position("e6"))).thenReturn(true);
        when(gameState.isNotBusy(new Position("e5"))).thenReturn(true);

        Collection<Position> movePositions = pawnMoveFinder.findPositionsToMove(Player.Black, new Position("e7"));
        assertThat(movePositions).containsOnly(new Position("e6"), new Position("e5"));
    }

    @Test
    public void shouldReturnAllProtectedPositions() {
        Collection<Position> movePositions = pawnMoveFinder.findPositionsUnderProtection(Player.White, new Position("e4"));
        assertThat(movePositions).containsOnly(new Position("d5"), new Position("f5"));
    }
}