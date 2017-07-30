package chess;

import chess.move.MoveFinderFactory;
import chess.pieces.*;

import java.util.List;
import java.util.stream.Collectors;

public class GameStateController {

    private final Board board;
    private final MoveFinderFactory moveFinderFactory;

    /**
     * Only for tests.
     */
    GameStateController(Board board, MoveFinderFactory moveFinderFactory) {
        this.board = board;
        this.moveFinderFactory = moveFinderFactory;
    }

    public GameStateController(Board board) {
        this.board = board;
        this.moveFinderFactory = new MoveFinderFactory(board);
    }

    public List<Move> getAvailableMoves(Player player) {
        return board.getCurrentPlayerPositions(player).stream()
                .map(position -> toListOfMoves(position, player))
                .flatMap(List::stream)
                .filter(move -> this.isAllowed(move, player))
                .collect(Collectors.toList());
    }

    private List<Move> toListOfMoves(Position position, Player currentPlayer) {
        Piece piece = board.getPieceAt(position);
        return findPositionsToMove(piece.getClass(), currentPlayer, position)
                .stream()
                .map(destinationPosition -> new Move(position, destinationPosition)).collect(Collectors.toList());
    }


    private List<Position> findPositionsToMove(Class pieceClass, Player player, Position from) {
        return moveFinderFactory.getMoveFinder(pieceClass).findPositionsToMove(player, from)
                .stream()
                .map(to -> new Move(from, to))
                .filter(move -> isAllowed(move, player))
                .map(Move::to)
                .collect(Collectors.toList());
    }

    private boolean isNotUnderAttack(Position position, Player player) {
        return !board.getOppositePlayerPositions(player).stream()
                .map(piecePosition -> this.toListOfProtectedPositions(piecePosition, player))
                .flatMap(List::stream)
                .anyMatch(position::equals);
    }

    public boolean isCheckMate(Player player) {
        return isCheck(player) && getAvailableMoves(player).isEmpty();
    }

    public boolean isStaleMate(Player player) {
        return !isCheck(player) && getAvailableMoves(player).isEmpty();
    }

    private List<Position> findPositionsToProtect(Class pieceClass, Player player, Position position) {
        return moveFinderFactory.getMoveFinder(pieceClass).findPositionsToProtect(player, position);
    }

    private List<Position> toListOfProtectedPositions(Position position, Player player) {
        Piece piece = board.getPieceAt(position);
        return findPositionsToProtect(piece.getClass(), player.opposite(), position);
    }

    private boolean isAllowed(Move move, Player player) {
        Board copy = board.copy();
        copy.move(move.from(), move.to());
        return !new GameStateController(copy).isCheck(player);
    }

    private boolean isCheck(Player player) {
        return !isNotUnderAttack(board.getKingPosition(player), player);
    }
}
