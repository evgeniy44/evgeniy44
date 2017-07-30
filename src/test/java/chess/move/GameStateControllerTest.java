package chess.move;

import chess.*;
import chess.pieces.Pawn;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameStateControllerTest {

    @Mock
    private Board board;

    @Mock
    private MoveFinder moveFinder;

//    @Test
//    public void shouldGetMovesOnSpecificMoveFinder() {
//        when(moveFinder.findPositionsToMove(Player.White, new Position("e2")))
//                .thenReturn(Lists.newArrayList(new Position("e4")));
//        when(board.isAllowed(new Move("e2", "e4"))).thenReturn(true);
//
//        HashMap<Class<?>, MoveFinder> moveFinders = new HashMap<>();
//        moveFinders.put(Pawn.class, moveFinder);
//        Position position = new Position("e2");
//        List<Position> positionsToMove = new GameStateController(board, moveFinders)
//                .findPositionsToMove(Pawn.class, Player.White, position);
//
//        assertThat(positionsToMove).containsOnly(new Position("e4"));
//        verify(moveFinder).findPositionsToMove(Player.White, position);
//    }

//    @Test
//    public void shouldGetProtectPositionsOnSpecificMoveFinder() {
//        when(moveFinder.findPositionsToProtect(Player.White, new Position("e2")))
//                .thenReturn(Lists.newArrayList(new Position("e4")));
//
//        HashMap<Class<?>, MoveFinder> moveFinders = new HashMap<>();
//        moveFinders.put(Pawn.class, moveFinder);
//        Position position = new Position("e2");
//        List<Position> positionsToMove = new GameStateController(board, moveFinders)
//                .findPositionsToProtect(Pawn.class, Player.White, position);
//
//        assertThat(positionsToMove).containsOnly(new Position("e4"));
//        verify(moveFinder).findPositionsToProtect(Player.White, position);
//    }
}