package chess;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void testOpposite() {
        assertEquals(Player.White.opposite(), Player.Black);
        assertEquals(Player.Black.opposite(), Player.White);
    }

}