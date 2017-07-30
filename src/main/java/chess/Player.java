package chess;

/**
 * Which side of the board is being played
 */
public enum Player {
    White, Black;

    public Player opposite() {
        return Player.White.equals(this) ? Player.Black : Player.White;
    }

}
