package chess.move;

import chess.GameState;
import chess.Player;
import chess.Position;
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
public class MoveFinderFacadeTest {

    @Mock
    private GameState gameState;

    @Mock
    private MoveFinder moveFinder;

    @Test
    public void shouldGetMovesOnSpecificMoveFinder() {
        when(moveFinder.findPositionsToMove(Player.White, new Position("e2")))
                .thenReturn(Lists.newArrayList(new Position("e4")));

        HashMap<Class<?>, MoveFinder> moveFinders = new HashMap<>();
        moveFinders.put(Pawn.class, moveFinder);
        Position position = new Position("e2");
        List<Position> positionsToMove = new MoveFinderFacade(gameState, moveFinders)
                .findPositionsToMove(Pawn.class, Player.White, position);

        assertThat(positionsToMove).containsOnly(new Position("e4"));
        verify(moveFinder).findPositionsToMove(Player.White, position);
    }

    @Test
    public void shouldGetProtectPositionsOnSpecificMoveFinder() {
        when(moveFinder.findPositionsToProtect(Player.White, new Position("e2")))
                .thenReturn(Lists.newArrayList(new Position("e4")));

        HashMap<Class<?>, MoveFinder> moveFinders = new HashMap<>();
        moveFinders.put(Pawn.class, moveFinder);
        Position position = new Position("e2");
        List<Position> positionsToMove = new MoveFinderFacade(gameState, moveFinders)
                .findPositionsToProtect(Pawn.class, Player.White, position);

        assertThat(positionsToMove).containsOnly(new Position("e4"));
        verify(moveFinder).findPositionsToProtect(Player.White, position);
    }
}