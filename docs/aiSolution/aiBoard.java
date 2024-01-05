import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    private ArrayList<Pit> pits = new ArrayList<Pit>();
    private Store store1;
    private Store store2;
    private Player p1;
    private Player p2;
    private Player currentPlayer;
    private boolean freeTurn = false;

    // Constructor
    public Board() {
        setUpPits();
        setUpStores();
    }

    // Returns the list of pits in the board, not including the stores
    public ArrayList<Pit> getPits() {
        return this.pits;
    }

    // Returns the list of stores in the board, not including the stores
    public ArrayList<Store> getStores() {
        return new ArrayList<>(Arrays.asList(store1, store2));
    }

    // Establishes 12 empty Pits in the board
    public void setUpPits() {
        for (int i = 0; i < 12; i++) {
            this.pits.add(new Pit());
        }
    }

    // Establishes 2 empty Stores in the board
    public void setUpStores() {
        store1 = new Store();
        store2 = new Store();
    }

    // Initializes the board by distributing stones
    public void initializeBoard() {
        for (Pit pit : pits) {
            for (int i = 0; i < 4; i++) {
                pit.addStone();
            }
        }
    }

    // Resets the board by redistributing stones but retains the players
    public void resetBoard() {
        store1.emptyStore();
        store2.emptyStore();

        for (Pit pit : pits) {
            pit.removeStones();
        }

        initializeBoard();
    }

    // Connects Players to their Stores, will need to call methods in store and in player
    // to ensure a two-way connection
    public void registerPlayers(Player one, Player two) {
        this.p1 = one;
        this.p2 = two;

        store1.setOwner(this.p1);
        this.p1.setStore(store1);

        store2.setOwner(this.p2);
        this.p2.setStore(store2);
    }

    // Moves stones for the player starting from a specific pit
    // Returns the total number of stones added to the corresponding store
    public int moveStones(int startPit, Player player) throws InvalidMoveException {
        currentPlayer = player;

        // Make sure each player starts on the correct side
        if ((startPit >= 1 && startPit <= 6 && currentPlayer == p1)
                || (startPit > 6 && startPit <= 12 && currentPlayer == p2)) {

            // Make sure there are stones in the pit
            if (pits.get(startPit - 1).getStoneCount() == 0) {
                throw new InvalidMoveException();
            }

            try {
                return distributeStones(startPit);
            } catch (PitNotFoundException pitNotFound) {
                // Handle exception for pitNotFound
            }
        }
        throw new InvalidMoveException();
    }
public int distributeStones(int startingPoint) throws PitNotFoundException {
    if (startingPoint < 1 || startingPoint > 12) {
        throw new PitNotFoundException();
    }

    int position = startingPoint - 1; // for array indexing
    int totalStones = getPitValue(position);
    setPitValue(position, 0); // Remove stones from the starting pit

    while (totalStones > 0) {
        position = (position + 1) % 14; // Circular indexing (0-13)

        // Skip opponent's store
        if ((position == 6 && currentPlayer == p2) || (position == 13 && currentPlayer == p1)) {
            continue;
        }

        // Add stone to the current pit
        if (position != 6 && position != 13) {
            addStoneToPit(position);
        }

        totalStones--;

        // If the last stone is dropped into an empty pit on the player's side, capture stones
        if (totalStones == 0 && position >= 1 && position <= 12 && getPitValue(position) == 1) {
            totalStones += captureStones(position);
        }
    }

    return totalStones;
}

private void addStoneToPit(int pitIndex) {
    getPits().get(pitIndex).addStone();
}

private void setPitValue(int pitIndex, int value) {
    getPits().get(pitIndex).setStoneCount(value);
}

private int getPitValue(int pitIndex) {
    return getPits().get(pitIndex).getStoneCount();
}
    // Captures stones from the opponent's pits
    // Returns the number of stones captured, if any
public int captureStones(int stoppingPoint) throws PitNotFoundException {
    if (stoppingPoint < 1 || stoppingPoint > 12) {
        throw new PitNotFoundException();
    }

    int stonesCaptured = 0;

    if (getPitValue(stoppingPoint) == 1) {
        int oppositePitIndex = 14 - stoppingPoint; // Index of the opposite pit
        stonesCaptured = getPitValue(oppositePitIndex) + getPitValue(stoppingPoint);

        setPitValue(stoppingPoint, 0); // Remove stones from the stopping pit
        setPitValue(oppositePitIndex, 0); // Remove stones from the opposite pit

        // Add captured stones to the current player's store
        if (currentPlayer == p1) {
            p1.getStore().addStones(stonesCaptured);
        } else {
            p2.getStore().addStones(stonesCaptured);
        }
    }

    // Gets the number of stones in a specific pit
public int getNumStones(int pitNum) throws PitNotFoundException {
    if (pitNum >= 1 && pitNum <= 12) {
        return pits.get(pitNum - 1).getStoneCount();
    } else {
        throw new PitNotFoundException();
    }
}

    // Indicates whether one side of the board is empty.
    // An empty side indicates the end of the game.
public boolean isSideEmpty(int pitNum) throws PitNotFoundException {
    boolean empty = true;

    if ((pitNum >= 1 && pitNum <= 6)) {
        for (int i = 0; i < 6; i++) {
            if (pits.get(i).getStoneCount() != 0) {
                empty = false;
                break;
            }
        }
    } else if ((pitNum > 6 && pitNum <= 12)) {
        for (int i = 6; i < 12; i++) {
            if (pits.get(i).getStoneCount() != 0) {
                empty = false;
                break;
             }
         }
    } else {
         throw new PitNotFoundException();
     }
    return empty;
}