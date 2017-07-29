package chess.move;

import chess.GameState;
import chess.Player;
import chess.Position;
import chess.PositionUtils;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StraightLinePieceMoveFinderTest {

    @Mock
    private GameState gameState;

    @Test
    public void shoudReturnAllPositionsByEndOfBoard() {
        StraightLinePieceMoveFinder bishopMoveFinder = new StraightLinePieceMoveFinder(gameState, Lists.newArrayList(
                PositionUtils::upAndRight, PositionUtils::upAndLeft,
                PositionUtils::downAndRight, PositionUtils::downAndLeft));

        when(gameState.isNotBusy(new Position("a2"))).thenReturn(true);
        when(gameState.isNotBusy(new Position("c2"))).thenReturn(true);
        when(gameState.isNotBusy(new Position("d3"))).thenReturn(true);
        when(gameState.isNotBusy(new Position("e4"))).thenReturn(true);
        when(gameState.isNotBusy(new Position("f5"))).thenReturn(true);
        when(gameState.isNotBusy(new Position("g6"))).thenReturn(true);
        when(gameState.isNotBusy(new Position("h7"))).thenReturn(true);

        List<Position> moves = bishopMoveFinder.findPositionsToMove(Player.White, new Position("b1"));

        assertThat(moves).containsOnly(new Position("a2"), new Position("c2"),
                new Position("d3"),  new Position("e4"), new Position("f5"),
                new Position("g6"), new Position("h7"));
    }

    @Test
    public void shoudReturnAllPositionsWithoutMyPiece() {
        StraightLinePieceMoveFinder bishopMoveFinder = new StraightLinePieceMoveFinder(gameState, Lists.newArrayList(
                PositionUtils::upAndRight, PositionUtils::upAndLeft,
                PositionUtils::downAndRight, PositionUtils::downAndLeft));

        when(gameState.containsMyPiece(new Position("a2"))).thenReturn(false);
        when(gameState.containsMyPiece(new Position("c2"))).thenReturn(false);
        when(gameState.containsMyPiece(new Position("d3"))).thenReturn(false);
        when(gameState.containsMyPiece(new Position("e4"))).thenReturn(false);
        when(gameState.containsMyPiece(new Position("f5"))).thenReturn(true);

        List<Position> moves = bishopMoveFinder.findPositionsToMove(Player.White, new Position("b1"));

        assertThat(moves).containsOnly(new Position("a2"), new Position("c2"),
                new Position("d3"),  new Position("e4"));
    }

    @Test
    public void shoudReturnAllPositionsWithOppositePiece() {
        StraightLinePieceMoveFinder bishopMoveFinder = new StraightLinePieceMoveFinder(gameState, Lists.newArrayList(
                PositionUtils::upAndRight, PositionUtils::upAndLeft,
                PositionUtils::downAndRight, PositionUtils::downAndLeft));

        when(gameState.containsMyPiece(new Position("a2"))).thenReturn(false);
        when(gameState.containsOppositePiece(new Position("a2"))).thenReturn(false);
        when(gameState.containsMyPiece(new Position("c2"))).thenReturn(false);
        when(gameState.containsOppositePiece(new Position("c2"))).thenReturn(false);
        when(gameState.containsMyPiece(new Position("d3"))).thenReturn(false);
        when(gameState.containsOppositePiece(new Position("d3"))).thenReturn(false);
        when(gameState.containsMyPiece(new Position("e4"))).thenReturn(false);
        when(gameState.containsOppositePiece(new Position("e4"))).thenReturn(false);
        when(gameState.containsMyPiece(new Position("f5"))).thenReturn(false);
        when(gameState.containsOppositePiece(new Position("f5"))).thenReturn(true);

        List<Position> moves = bishopMoveFinder.findPositionsToMove(Player.White, new Position("b1"));

        assertThat(moves).containsOnly(new Position("a2"), new Position("c2"),
                new Position("d3"),  new Position("e4"), new Position("f5"));
    }
}
