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

@RunWith(MockitoJUnitRunner.class)
public class StraightLinePieceMoveFinderTest extends BaseMoveFinderTest {

    @Mock
    private GameState gameState;

    @InjectMocks
    private StraightLinePieceMoveFinder moveFinder = new StraightLinePieceMoveFinder(gameState, Lists.newArrayList(
            PositionUtils::upAndRight, PositionUtils::upAndLeft,
            PositionUtils::downAndRight, PositionUtils::downAndLeft));

    @Test
    public void shouldReturnAllPositionsByEndOfBoard() {
        notBusyPositions(gameState, "a2", "c2", "d3", "e4", "f5", "g6", "h7");

        List<Position> moves = moveFinder.findPositionsToMove(Player.White, new Position("b1"));
        assertContainsOnlyPositions(moves, "a2", "c2", "d3", "e4", "f5", "g6", "h7");
    }

    @Test
    public void shoudReturnAllPositionsByEndOfBoardUnderProtection() {
        notBusyPositions(gameState, "a2", "c2", "d3", "e4", "f5", "g6", "h7");

        List<Position> moves = moveFinder.findPositionsToProtect(Player.White, new Position("b1"));
        assertContainsOnlyPositions(moves, "a2", "c2", "d3", "e4", "f5", "g6", "h7");
    }

    @Test
    public void shoudReturnAllPositionsWithoutMyPiece() {
        notBusyPositions(gameState, "a2", "c2", "d3", "e4");
        busyPositions(gameState, "f5");
        myPositions(gameState, "f5");

        List<Position> moves = moveFinder.findPositionsToMove(Player.White, new Position("b1"));
        assertContainsOnlyPositions(moves, "a2", "c2", "d3", "e4");
    }

    @Test
    public void shoudReturnAllPositionsWithMyPieceUnderProtection() {
        notBusyPositions(gameState, "a2", "c2", "d3", "e4");
        busyPositions(gameState, "f5");
        myPositions(gameState, "f5");

        List<Position> moves = moveFinder.findPositionsToProtect(Player.White, new Position("b1"));
        assertContainsOnlyPositions(moves, "a2", "c2", "d3", "e4", "f5");
    }

    @Test
    public void shoudReturnAllPositionsWithOppositePiece() {
        notBusyPositions(gameState, "a2", "c2", "d3", "e4");
        busyPositions(gameState, "f5");
        notMyPositions(gameState, "f5");

        List<Position> moves = moveFinder.findPositionsToMove(Player.White, new Position("b1"));
        assertContainsOnlyPositions(moves, "a2", "c2", "d3", "e4", "f5");
    }

    @Test
    public void shoudReturnAllPositionsWithOppositePieceUnderProtection() {
        notBusyPositions(gameState, "a2", "c2", "d3", "e4");
        busyPositions(gameState, "f5");
        notMyPositions(gameState, "f5");

        List<Position> moves = moveFinder.findPositionsToProtect(Player.White, new Position("b1"));
        assertContainsOnlyPositions(moves, "a2", "c2", "d3", "e4", "f5");
    }
}
