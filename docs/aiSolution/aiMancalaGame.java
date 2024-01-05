import java.util.ArrayList;

public class MancalaGame {
    private ArrayList<Player> players;
    private Player currentPlayer;
    private Board board;

    // Constructor
    public MancalaGame() {
        players = new ArrayList<>();
    }

    // Sets the players for the game
    public void setPlayers(Player onePlayer, Player twoPlayer) {
        players.clear();
        players.add(onePlayer);
        players.add(twoPlayer);
    }

    // Gets the players for the game
    public ArrayList<Player> getPlayers() {
        return players;
    }

    // Gets the current player
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    // Sets the current player
    public void setCurrentPlayer(Player player) {
        currentPlayer = player;
    }

    // Sets the board for the game
    public void setBoard(Board theBoard) {
        board = theBoard;
    }

    // Gets the board for the game
    public Board getBoard() {
        return board;
    }

    // Gets the number of stones in a specific pit
    public int getNumStones(int pitNum) throws PitNotFoundException {
        return board.getNumStones(pitNum);
    }

    // Makes a move for the current player, returning the total number of stones remaining in the player's side pits
    public int move(int startPit) throws InvalidMoveException {
        try {
            int totalStones = board.moveStones(startPit, currentPlayer);
            // Switch the current player after a move
            currentPlayer = (currentPlayer == players.get(0)) ? players.get(1) : players.get(0);
            return totalStones;
        } catch (PitNotFoundException e) {
            throw new InvalidMoveException("Invalid move: Pit not found.");
        }
    }

    // Gets the total number of stones in a player's store
    public int getStoreCount(Player player) throws NoSuchPlayerException {
        if (players.contains(player)) {
            return player.getStore().getTotalStones();
        } else {
            throw new NoSuchPlayerException("Player not found.");
        }
    }

    // Gets the winner of the game
    public Player getWinner() throws GameNotOverException {
        if (!isGameOver()) {
            throw new GameNotOverException("Game is not over yet.");
        }

        int storeCountPlayer1 = playerStoreCount(players.get(0));
        int storeCountPlayer2 = playerStoreCount(players.get(1));

        if (storeCountPlayer1 > storeCountPlayer2) {
            return players.get(0);
        } else if (storeCountPlayer1 < storeCountPlayer2) {
            return players.get(1);
        } else {
            return null; // It's a tie
        }
    }

    // Checks if the game is over
    public boolean isGameOver() {
        return board.isSideEmpty(1) || board.isSideEmpty(7);
    }

    // Starts a new game by resetting the board
    public void startNewGame() {
        board.resetBoard();
    }

    // Helper method to get the total stone count in a player's store
    private int playerStoreCount(Player player) {
        return player.getStore().getTotalStones();
    }
}
