package chess.move;

import chess.*;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RookMoveFinderTest {

    @Mock
    private Board board;

    @Test
    public void shoudReturnAllPositionsByEndOfBoard() {
        MoveFinder moveFinder = new StraightLinePieceMoveFinder(board, Lists.newArrayList(
                PositionUtils::up, PositionUtils::left,
                PositionUtils::down, PositionUtils::right));

        isNotBusyTrueForPositions("a2", "a3", "a4", "a5", "a6", "a7", "a8");
        isNotBusyTrueForPositions("b1", "c1", "d1", "e1", "f1", "g1", "h1");

        List<Position> moves = moveFinder.findPositionsToMove(Player.White, new Position("a1"));

        assertThat(moves).containsOnly(
                new Position("a2"), new Position("a3"), new Position("a4"),
                new Position("a5"), new Position("a6"), new Position("a7"),
                new Position("a8"), new Position("b1"), new Position("c1"),
                new Position("d1"),  new Position("e1"), new Position("f1"),
                new Position("g1"), new Position("h1"));
    }

    private void isNotBusyTrueForPositions(String... positions) {
        for (String position : positions) {
            when(board.isNotOccupied(new Position(position))).thenReturn(true);
        }
    }
}