public class Pit {
    private int stoneCount;

    // Constructor
    public Pit() {
        this.stoneCount = 0;
    }

    // Gets the number of stones in the pit
    public int getStoneCount() {
        return stoneCount;
    }

    // Adds a stone to the pit
    public void addStone() {
        stoneCount++;
    }

    // Removes and returns the stones from the pit
    // Returns: the number of stones removed from the pit
    public int removeStones() {
        int removedStones = stoneCount;
        stoneCount = 0; // Reset the pit's stone count
        return removedStones;
    }
}
