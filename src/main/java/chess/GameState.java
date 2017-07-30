package chess;


import chess.pieces.*;

import java.util.List;

/**
 * Class that represents the current state of the game.  Basically, what pieces are in which positions on the
 * board.
 */
public class GameState {

    public static final int BLACK_START_ROW = 7;
    public static final int WHITE_START_ROW = 2;

    /**
     * The current player
     */
    private Player currentPlayer = Player.White;

    private GameStateController moveFinder;
    private Board board;

    /**
     * Create the game state.
     */
    public GameState() {
        board = new Board();
        moveFinder = new GameStateController(board);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Call to initialize the game state into the starting positions
     */
    public void reset() {
        board.reset();
    }

    public List<Move> getAvailableMoves() {
        return moveFinder.getAvailableMoves(currentPlayer);
    }

    public void move(Position from, Position to) {
        validateMove(from, to);
        board.move(from, to);
        switchPlayer();
    }

    private void switchPlayer() {
        currentPlayer = currentPlayer.opposite();
    }

    public void move(String from, String to) {
        move(new Position(from), new Position(to));
    }

    private Piece validateMove(Position from, Position to) {
        Piece piece = board.getPieceAt(from);
        if (piece  == null) {
            throw new IllegalArgumentException("No piece at " + from + " position");
        }
        if (!getAvailableMoves().contains(new Move(from, to))) {
            throw new IllegalArgumentException(piece.getClass().getSimpleName()
                    + " can't move from " + from + " to " + to);
        }
        return piece;
    }

    public boolean isCheckMate() {
        return moveFinder.isCheckMate(currentPlayer);
    }

    public boolean isStaleMate() {
        return moveFinder.isStaleMate(currentPlayer);
    }

    public Board getBoard() {
        return board;
    }
}
