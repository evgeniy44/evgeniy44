package chess.move;

import chess.Board;
import chess.pieces.*;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MoveFinderFactoryTest {

    @Test
    public void testAppropriateMoveFinders() {
        MoveFinderFactory moveFinderFactory = new MoveFinderFactory(new Board());
        assertThat(moveFinderFactory.getMoveFinder(Pawn.class)).isExactlyInstanceOf(PawnMoveFinder.class);
        assertThat(moveFinderFactory.getMoveFinder(Bishop.class)).isExactlyInstanceOf(StraightLinePieceMoveFinder.class);
        assertThat(moveFinderFactory.getMoveFinder(Knight.class)).isExactlyInstanceOf(KnightMoveFinder.class);
        assertThat(moveFinderFactory.getMoveFinder(Queen.class)).isExactlyInstanceOf(StraightLinePieceMoveFinder.class);
        assertThat(moveFinderFactory.getMoveFinder(Rook.class)).isExactlyInstanceOf(StraightLinePieceMoveFinder.class);
        assertThat(moveFinderFactory.getMoveFinder(String.class)).isNull();
    }
}