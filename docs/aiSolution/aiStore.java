public class Store {
    private Player owner;
    private int stoneCount;

    // Sets the owner of the store
    public void setOwner(Player player) {
        this.owner = player;
    }

    // Gets the owner of the store
    // Returns: the owner of the store
    public Player getOwner() {
        return owner;
    }

    // Adds stones to the store
    public void addStones(int amount) {
        stoneCount += amount;
    }

    // Gets the total number of stones in the store
    // Returns: the total number of stones in the store
    public int getTotalStones() {
        return stoneCount;
    }

    // Empties the store and returns the number of stones that were in it
    // Returns: the number of stones in the store
    public int emptyStore() {
        int removedStones = stoneCount;
        stoneCount = 0; // Reset the store's stone count
        return removedStones;
    }
}
