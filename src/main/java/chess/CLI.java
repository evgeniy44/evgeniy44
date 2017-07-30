package chess;

import chess.pieces.Piece;

import java.io.*;

/**
 * This class provides the basic CLI interface to the Chess game.
 */
public class CLI {
    private static final String NEWLINE = System.getProperty("line.separator");
    public static final String POSITION_REGEX = "[a-h][1-8]";

    private final BufferedReader inReader;
    private final PrintStream outStream;

    private GameState gameState = null;

    public CLI(InputStream inputStream, PrintStream outStream) {
        this.inReader = new BufferedReader(new InputStreamReader(inputStream));
        this.outStream = outStream;
        writeOutput("Welcome to Chess!");
    }

    /**
     * Write the string to the output
     * @param str The string to write
     */
    void writeOutput(String str) {
        this.outStream.println(str);
    }

    /**
     * Retrieve a string from the console, returning after the user hits the 'Return' key.
     * @return The input from the user, or an empty-length string if they did not type anything.
     */
    private String getInput() {
        try {
            this.outStream.print("> ");
            return inReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read from input: ", e);
        }
    }

    void startEventLoop() {
        writeOutput("Type 'help' for a list of commands.");
        doNewGame();

        while (true) {
            showBoard();
            if (gameState.isCheckMate()) {
                writeOutput("The game is over.  Congrats to " + gameState.getCurrentPlayer().opposite());
                break;
            }
            if (gameState.isStaleMate()) {
                writeOutput("The game is over.  Stalemate");
                break;
            }
            writeOutput(gameState.getCurrentPlayer() + "'s Move");

            String input = getInput();
            if (input == null) {
                break; // No more input possible; this is the only way to exit the event loop
            } else if (input.length() > 0) {
                if (input.equals("help")) {
                    showCommands();
                } else if (input.equals("new")) {
                    doNewGame();
                } else if (input.equals("quit")) {
                    writeOutput("Goodbye!");
                    System.exit(0);
                } else if (input.equals("board")) {
                    writeOutput("Current Game:");
                } else if (input.equals("list")) {
                    writeOutput("See list of available move below:");
                    gameState.getAvailableMoves().forEach((move) -> this.writeOutput(move.toString()));
                } else if (input.startsWith("move")) {
                    handleMoveCommand(input);
                } else if (input.equals("hint")) {
                    writeOutput("Лошадью ходи, век воли не видать!");
                } else {
                    writeOutput("I didn't understand that.  Type 'help' for a list of commands.");
                }
            }
        }
    }

    private void handleMoveCommand(String input) {
        try {
            if (isValidForMove(input)) {
                gameState.move(input.split(" ")[1], input.split(" ")[2]);
            } else {
                writeOutput("Invalid format of 'move' command");
            }
        } catch (IllegalArgumentException e) {
            writeOutput("Can't proceed with given move due to: " + e.getMessage());
        }
    }

    private boolean isValidForMove(String input) {
        String[] parts = input.split(" ");
        if (parts.length != 3) {
            return false;
        }
        if (!"move".equals(parts[0]) || !parts[1].matches(POSITION_REGEX) || !parts[2].matches(POSITION_REGEX)) {
            return false;
        }
        return true;
    }

    private void doNewGame() {
        gameState = new GameState();
        gameState.reset();
    }

    private void showBoard() {
        writeOutput(getBoardAsString());
    }

    private void showCommands() {
        writeOutput("Possible commands: ");
        writeOutput("    'help'                       Show this menu");
        writeOutput("    'quit'                       Quit Chess");
        writeOutput("    'new'                        Create a new game");
        writeOutput("    'board'                      Show the chess board");
        writeOutput("    'list'                       List all possible moves");
        writeOutput("    'move <colrow> <colrow>'     Make a move");
    }

    /**
     * Display the board for the user(s)
     */
    String getBoardAsString() {
        StringBuilder builder = new StringBuilder();
        builder.append(NEWLINE);

        printColumnLabels(builder);
        for (int i = Position.MAX_ROW; i >= Position.MIN_ROW; i--) {
            printSeparator(builder);
            printSquares(i, builder);
        }

        printSeparator(builder);
        printColumnLabels(builder);

        return builder.toString();
    }


    private void printSquares(int rowLabel, StringBuilder builder) {
        builder.append(rowLabel);

        for (char c = Position.MIN_COLUMN; c <= Position.MAX_COLUMN; c++) {
            Piece piece = gameState.getBoard().getPieceAt(String.valueOf(c) + rowLabel);
            char pieceChar = piece == null ? ' ' : piece.getIdentifier();
            builder.append(" | ").append(pieceChar);
        }
        builder.append(" | ").append(rowLabel).append(NEWLINE);
    }

    private void printSeparator(StringBuilder builder) {
        builder.append("  +---+---+---+---+---+---+---+---+").append(NEWLINE);
    }

    private void printColumnLabels(StringBuilder builder) {
        builder.append("   ");
        for (char c = Position.MIN_COLUMN; c <= Position.MAX_COLUMN; c++) {
            builder.append(" ").append(c).append("  ");
        }

        builder.append(NEWLINE);
    }

    public static void main(String[] args) {
        CLI cli = new CLI(System.in, System.out);
        cli.startEventLoop();
    }
}
