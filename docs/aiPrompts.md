## Pit Prompt:
Create a java class named "Pit" that represents a pit in the Mancala game, with the following functions: public int getStoneCount() gets the number of stones in the pit. Public void addStone()
adds a stone to the pit. public int removeStones() removes and returns the stones from the pit.
Returns: the number of stones removed from the pit.  (No main method)

## Store Prompt:
Create a java class named "Store.java" that represents a store in the Mancala game, with the following functions: public void setOwner(Player player) sets the owner of the store. public Player getOwner() gets the owner of the store. Returns the owner of the store. public void addStones(int amount) adds stones to the store. public int getTotalStones() gets the total number of stones in the store, returns the total number of stones in the store. public int emptyStore() empties the store and returns the number of stones that were in it, returns the number of stones in the store. (No main method)

## Player Prompt:
Create a java class named "Player.java" that represents a player in the Mancala game, with the following functions: public String getName() gets the name of the player. Returns: the player's name. public void setName(String name) sets the player's name. public Store getStore() gets the player's store where they collect stones. returns: the player's store. public int getStoreCount()
gets the count of the number of stones in the player's store where they collect stones. Returns: the count of stones in the player's store. public void setStore(Store store) sets the player's store. (No main method)

## Board Prompt:

* 1. Create a java class named "Board.java" that represents the board in the Mancala game, with the following functions: public void setUpPits() establishes 12 empty Pits in the board. public ArrayList<Pit> getPits() returns the list of pits in the board, not including the stores. public ArrayList<Store> getStores() returns the list of stores in the board, not including the stores. public void setUpStores() establishes 2 empty Stores in the board. public void initializeBoard() initializes the board by distributing stones. public void resetBoard() resets the board by redistributing stones but retains the players. public void registerPlayers(Player one, Player two) connects Players to their Stores, will need to call methods in store and in player to ensure a two-way connection. public int moveStones(int startPit, Player player) throws InvalidMoveException, moves stones for the player starting from a specific pit, returns the total number of stones added to the corresponding store. public int distributeStones(int startingPoint) throws PitNotFoundException is a helper method that distributes stones into pits and stores, skipping the opponent's store, returns the total number of stones added to pits and stores. public int captureStones(int stoppingPoint) throws PitNotFoundException captures stones from the opponent's pits, returns the number of stones captured, if any. public int getNumStones(int pitNum) throws PitNotFoundException gets the number of stones in a specific pit. public boolean isSideEmpty(int pitNum) throws PitNotFoundException indicates whether one side of the board is empty. An empty side indicates the end of the game. more context: pits 1-6 are on one side of the board while pits 7-12 are on the other side of the board. if this method were called with a 3 as a parameter, it would return true if pits 1-6 were empty, false otherwise. (No main method)

* 2. Can you implement everything regarded as a basic mancala game


## MancalaGame Prompt:
Create a java class named "MancalaGame.java" that represents the game in the Mancala game, with the following functions: public void setPlayers(Player onePlayer, Player twoPlayer) sets the players for the game. public ArrayList<Player> getPlayers() gets the players for the game. public Player getCurrentPlayer() gets the current player. public void setCurrentPlayer(Player player) sets the current player. public void setBoard(Board theBoard) sets the board for the game. public Board getBoard() gets the board for the game. public int getNumStones(int pitNum) throws PitNotFoundException gets the number of stones in a specific pit. public int move(int startPit) throws InvalidMoveException makes a move for the current player, returning the total number of stones remaining in the players side pits. public int getStoreCount(Player player) throws NoSuchPlayerException gets the total number of stones in a player's store, returns the total number of stones in the player's store. public Player getWinner() throws GameNotOverException gets the winner of the game, returns winning player or null for a tie. public boolean isGameOver() checks if the game is over, true if over, false otherwise. public void startNewGame() starts a new game by resetting the board. (no main method required)

## Pit Exception Prompt:
Can you write me a file named "PitNotFoundException.java" that displays an error message for invalid pit. public PitNotFoundException()

## Game Not Over Prompt:
Can you write a file named "GameNotOverException.java" that displays an exception message that the game isn't over. public GameNotOverException()

## Invalid Move Prompt:
Can you write a file named "InvalidMoveException.java" that displays an error message for invalid moves. public InvalidMoveException()

## No Such Player Prompt:
Can you write a file named "NoSuchPlayerException.java" that displays an error message for palyer not found. public NoSuchPlayerException()