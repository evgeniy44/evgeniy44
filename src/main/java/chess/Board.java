package chess;

import chess.pieces.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {

    /**
     * A map of board positions to pieces at that position
     */
    private Map<Position, Piece> positionToPieceMap = new HashMap<>();

    public void reset() {
        // White Pieces
        placePiece(new Rook(Player.White), new Position("a1"));
        placePiece(new Knight(Player.White), new Position("b1"));
        placePiece(new Bishop(Player.White), new Position("c1"));
        placePiece(new Queen(Player.White), new Position("d1"));
        placePiece(new King(Player.White), new Position("e1"));
        placePiece(new Bishop(Player.White), new Position("f1"));
        placePiece(new Knight(Player.White), new Position("g1"));
        placePiece(new Rook(Player.White), new Position("h1"));
        placePiece(new Pawn(Player.White), new Position("a2"));
        placePiece(new Pawn(Player.White), new Position("b2"));
        placePiece(new Pawn(Player.White), new Position("c2"));
        placePiece(new Pawn(Player.White), new Position("d2"));
        placePiece(new Pawn(Player.White), new Position("e2"));
        placePiece(new Pawn(Player.White), new Position("f2"));
        placePiece(new Pawn(Player.White), new Position("g2"));
        placePiece(new Pawn(Player.White), new Position("h2"));

        // Black Pieces
        placePiece(new Rook(Player.Black), new Position("a8"));
        placePiece(new Knight(Player.Black), new Position("b8"));
        placePiece(new Bishop(Player.Black), new Position("c8"));
        placePiece(new Queen(Player.Black), new Position("d8"));
        placePiece(new King(Player.Black), new Position("e8"));
        placePiece(new Bishop(Player.Black), new Position("f8"));
        placePiece(new Knight(Player.Black), new Position("g8"));
        placePiece(new Rook(Player.Black), new Position("h8"));
        placePiece(new Pawn(Player.Black), new Position("a7"));
        placePiece(new Pawn(Player.Black), new Position("b7"));
        placePiece(new Pawn(Player.Black), new Position("c7"));
        placePiece(new Pawn(Player.Black), new Position("d7"));
        placePiece(new Pawn(Player.Black), new Position("e7"));
        placePiece(new Pawn(Player.Black), new Position("f7"));
        placePiece(new Pawn(Player.Black), new Position("g7"));
        placePiece(new Pawn(Player.Black), new Position("h7"));
    }

    /**
     * Get the piece at the position specified by the String
     * @param colrow The string indication of position; i.e. "d5"
     * @return The piece at that position, or null if it does not exist.
     */
    public Piece getPieceAt(String colrow) {
        Position position = new Position(colrow);
        return getPieceAt(position);
    }

    /**
     * Get the piece at a given position on the board
     * @param position The position to inquire about.
     * @return The piece at that position, or null if it does not exist.
     */
    public Piece getPieceAt(Position position) {
        return positionToPieceMap.get(position);
    }

    /**
     * Method to place a piece at a given position
     * @param piece The piece to place
     * @param position The position
     */
    private void placePiece(Piece piece, Position position) {
        positionToPieceMap.put(position, piece);
    }

    public Board copy() {
        Board board = new Board();
        board.positionToPieceMap.putAll(this.positionToPieceMap);
        return board;
    }

    public void move(Position from, Position to) {
        Piece piece = positionToPieceMap.remove(from);
        positionToPieceMap.put(to, piece);
    }

    public List<Position> getOppositePlayerPositions(Player player) {
        return positionToPieceMap.keySet().stream()
                .filter(position->this.isOppositePlayerPieceOnPosition(position, player))
                .collect(Collectors.toList());
    }

    private boolean isOppositePlayerPieceOnPosition(Position position, Player player) {
        return positionToPieceMap.get(position).getOwner().equals(player.opposite());
    }

    private boolean isCurrentPlayerKingOnPosition(Position position, Player currentPlayer) {
        return positionToPieceMap.get(position).getOwner().equals(currentPlayer)
                && positionToPieceMap.get(position) instanceof King;
    }

    public List<Position> getCurrentPlayerPositions(Player player) {
        return positionToPieceMap.keySet().stream()
                .filter(position -> this.isCurrentPlayerPieceOnPosition(position, player))
                .collect(Collectors.toList());
    }

    public Position getKingPosition(Player player) {
        return positionToPieceMap.keySet().stream()
                .filter(position -> this.isCurrentPlayerKingOnPosition(position, player))

                .findFirst().get();
    }

    private boolean isCurrentPlayerPieceOnPosition(Position position, Player currentPlayer) {
        return positionToPieceMap.get(position).getOwner().equals(currentPlayer);
    }

    public boolean isNotOccupied(Position position) {
        return getPieceAt(position) == null;
    }

    public boolean containsOppositePiece(Position position, Player currentPlayer) {
        return getPieceAt(position) != null && !currentPlayer.equals(getPieceAt(position).getOwner());
    }

    public boolean containsMyPiece(Position position, Player currentPlayer) {
        return getPieceAt(position) != null && currentPlayer.equals(getPieceAt(position).getOwner());
    }
}
