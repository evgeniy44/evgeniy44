package chess.move;

import chess.*;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class StraightLinePieceMoveFinderTest extends BaseMoveFinderTest {

    @Mock
    private Board board;

    @InjectMocks
    private StraightLinePieceMoveFinder moveFinder = new StraightLinePieceMoveFinder(board, Lists.newArrayList(
            PositionUtils::upAndRight, PositionUtils::upAndLeft,
            PositionUtils::downAndRight, PositionUtils::downAndLeft));

    @Test
    public void shouldReturnAllPositionsByEndOfBoard() {
        notBusyPositions(board, "a2", "c2", "d3", "e4", "f5", "g6", "h7");

        List<Position> moves = moveFinder.findPositionsToMove(Player.White, new Position("b1"));
        assertContainsOnlyPositions(moves, "a2", "c2", "d3", "e4", "f5", "g6", "h7");
    }

    @Test
    public void shoudReturnAllPositionsByEndOfBoardUnderProtection() {
        notBusyPositions(board, "a2", "c2", "d3", "e4", "f5", "g6", "h7");

        List<Position> moves = moveFinder.findPositionsToProtect(Player.White, new Position("b1"));
        assertContainsOnlyPositions(moves, "a2", "c2", "d3", "e4", "f5", "g6", "h7");
    }

    @Test
    public void shoudReturnAllPositionsWithoutMyPiece() {
        notBusyPositions(board, "a2", "c2", "d3", "e4");
        busyPositions(board, "f5");
        myPositions(board, Player.White, "f5");

        List<Position> moves = moveFinder.findPositionsToMove(Player.White, new Position("b1"));
        assertContainsOnlyPositions(moves, "a2", "c2", "d3", "e4");
    }

    @Test
    public void shoudReturnAllPositionsWithMyPieceUnderProtection() {
        notBusyPositions(board, "a2", "c2", "d3", "e4");
        busyPositions(board, "f5");
        myPositions(board, Player.White, "f5");

        List<Position> moves = moveFinder.findPositionsToProtect(Player.White, new Position("b1"));
        assertContainsOnlyPositions(moves, "a2", "c2", "d3", "e4", "f5");
    }

    @Test
    public void shoudReturnAllPositionsWithOppositePiece() {
        notBusyPositions(board, "a2", "c2", "d3", "e4");
        busyPositions(board, "f5");
        notMyPositions(board, Player.White, "f5");

        List<Position> moves = moveFinder.findPositionsToMove(Player.White, new Position("b1"));
        assertContainsOnlyPositions(moves, "a2", "c2", "d3", "e4", "f5");
    }

    @Test
    public void shoudReturnAllPositionsWithOppositePieceUnderProtection() {
        notBusyPositions(board, "a2", "c2", "d3", "e4");
        busyPositions(board, "f5");
        notMyPositions(board, Player.White, "f5");

        List<Position> moves = moveFinder.findPositionsToProtect(Player.White, new Position("b1"));
        assertContainsOnlyPositions(moves, "a2", "c2", "d3", "e4", "f5");
    }
}
