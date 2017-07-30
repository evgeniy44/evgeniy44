package chess.end2end;

import chess.*;
import chess.pieces.Piece;
import chess.pieces.Queen;
import chess.pieces.Rook;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Basic unit tests for the GameState class
 */
public class GameStateTest {

    private GameState state;

    @Before
    public void setUp() {
        state = new GameState();
    }

    @Test
    public void testStartsEmpty() {
        // Make sure all the positions are empty
        for (char col = Position.MIN_COLUMN; col <= Position.MAX_COLUMN; col++) {
            for (int row = Position.MIN_ROW; row <= Position.MAX_ROW; row++) {
                assertNull("All pieces should be empty", state.getBoard().getPieceAt(String.valueOf(col) + row));
            }
        }
    }

    @Test
    public void testInitialGame() {
        // Start the game
        state.reset();

        // White should be the first player
        Player current = state.getCurrentPlayer();
        assertEquals("The initial player should be White", Player.White, current);

        // Spot check a few pieces
        Piece whiteRook = state.getBoard().getPieceAt("a1");
        assertTrue("A rook should be at a1", whiteRook instanceof Rook);
        assertEquals("The rook at a1 should be owned by White", Player.White, whiteRook.getOwner());


        Piece blackQueen = state.getBoard().getPieceAt("d8");
        assertTrue("A queen should be at d8", blackQueen instanceof Queen);
        assertEquals("The queen at d8 should be owned by Black", Player.Black, blackQueen.getOwner());
    }

    @Test(expected = IllegalArgumentException.class)
    public void unaccepatbleMoveShouldFail() {
        state.reset();
        state.move("e3", "e5");
    }

    @Test
    public void testAllowedStepsUnderCheck() {
        state.reset();
        state.move("e2", "e4");
        state.move("a7", "a6");
        state.move("d1", "h5");
        state.move("a6", "a5");
        state.move("h5", "f7");

        assertThat(state.getAvailableMoves()).containsOnly(new Move("e8", "f7"));
    }

    @Test
    public void testMultipleAllowedStepsUnderCheck() {
        state.reset();
        state.move("e2", "e4");
        state.move("e7", "e5");
        state.move("d1", "h5");
        state.move("d7", "d6");
        state.move("h5", "e5");

        assertThat(state.getAvailableMoves()).containsOnly(
                new Move("e8", "d7"),
                new Move("c8", "e6"),
                new Move("d8", "e7"),
                new Move("f8", "e7"),
                new Move("d6", "e5"),
                new Move("g8", "e7")
        );
    }

    @Test
    public void testCheckMate() {
        state.reset();
        state.move("g2", "g4");
        state.move("e7", "e6");
        state.move("f2", "f3");
        state.move("d8", "h4");

        assertTrue(state.isCheckMate());
    }

    @Test
    public void testStaleMate() {
        state.reset();
        state.move("d2", "d4");
        state.move("e7", "e5");
        state.move("d1", "d2");
        state.move("e5", "e4");

        state.move("d2", "f4");
        state.move("f7", "f5");

        state.move("h2", "h3");
        state.move("f8", "b4");

        state.move("b1", "d2");
        state.move("d7", "d6");

        state.move("f4", "h2");
        state.move("c8", "e6");

        state.move("a2", "a4");
        state.move("d8", "h4");

        state.move("a1", "a3");
        state.move("c7", "c5");

        state.move("a3", "g3");
        state.move("f5", "f4");

        state.move("f2", "f3");
        state.move("e6", "b3");

        state.move("d4", "d5");
        state.move("b4", "a5");

        state.move("c2", "c4");
        state.move("e4", "e3");

        assertTrue(state.isStaleMate());
    }

    @Test
    public void testAvailableMoves() {
        state.reset();

        List<Move> availableMoves = state.getAvailableMoves();
        assertThat(availableMoves).contains(new Move("e2", "e4"));
        assertThat(availableMoves).contains(new Move("e2", "e3"));
        assertThat(availableMoves).contains(new Move("b1", "c3"));
    }
}
