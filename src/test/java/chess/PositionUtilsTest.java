package chess;

import chess.Position;
import org.junit.Test;

import static org.junit.Assert.*;

public class PositionUtilsTest {

    @Test
    public void upShouldReturnNullIfItsOutOfTheBoard() {
        assertNull(PositionUtils.up(new Position("h8")));
    }

    @Test
    public void upShouldReturnNotNullIfItsOnTheBoard() {
        assertEquals(PositionUtils.up(new Position("h7")), new Position("h8"));
    }

    @Test
    public void downShouldReturnNullIfItsOutOfTheBoard() {
        assertNull(PositionUtils.down(new Position("h1")));
    }

    @Test
    public void downShouldReturnNotNullIfItsOnTheBoard() {
        assertEquals(PositionUtils.down(new Position("h7")), new Position("h6"));
    }

    @Test
    public void leftShouldReturnNullIfItsOutOfTheBoard() {
        assertNull(PositionUtils.left(new Position("a2")));
    }

    @Test
    public void leftShouldReturnNotNullIfItsOnTheBoard() {
        assertEquals(PositionUtils.left(new Position("b2")), new Position("a2"));
    }

    @Test
    public void roghtShouldReturnNullIfItsOutOfTheBoard() {
        assertNull(PositionUtils.right(new Position("h2")));
    }

    @Test
    public void rightShouldReturnNotNullIfItsOnTheBoard() {
        assertEquals(PositionUtils.right(new Position("b2")), new Position("c2"));
    }

    @Test
    public void upAndRightShouldReturnNullIfItsOutOfTheBoard() {
        assertNull(PositionUtils.upAndRight(new Position("b8")));
    }

    @Test
    public void upAndRightShouldReturnNotNullIfItsOnTheBoard() {
        assertEquals(PositionUtils.upAndRight(new Position("b2")), new Position("c3"));
    }

    @Test
    public void upAndLeftShouldReturnNullIfItsOutOfTheBoard() {
        assertNull(PositionUtils.upAndLeft(new Position("a1")));
    }

    @Test
    public void upAndLeftShouldReturnNotNullIfItsOnTheBoard() {
        assertEquals(PositionUtils.upAndLeft(new Position("b2")), new Position("a3"));
    }

    @Test
    public void downAndLeftShouldReturnNullIfItsOutOfTheBoard() {
        assertNull(PositionUtils.downAndLeft(new Position("a3")));
    }

    @Test
    public void downAndLeftShouldReturnNotNullIfItsOnTheBoard() {
        assertEquals(PositionUtils.downAndLeft(new Position("b2")), new Position("a1"));
    }

    @Test
    public void downAndRightShouldReturnNullIfItsOutOfTheBoard() {
        assertNull(PositionUtils.downAndRight(new Position("h5")));
    }

    @Test
    public void downAndRightShouldReturnNotNullIfItsOnTheBoard() {
        assertEquals(PositionUtils.downAndRight(new Position("b2")), new Position("c1"));
    }


    @Test
    public void moveShouldReturnNullIfItsOnTheBoard() {
        assertNull(PositionUtils.move(new Position("b2"), PositionUtils::left, PositionUtils::left,
                PositionUtils::left));
    }

    @Test
    public void moveShouldReturnNotNullIfItsOnTheBoard() {
        assertEquals(PositionUtils.move(new Position("b2"), PositionUtils::right, PositionUtils::right,
                PositionUtils::up, PositionUtils::up), new Position("d4"));
    }
}