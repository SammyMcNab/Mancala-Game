public class Player {
    private String name;
    private Store store;

    // Constructor
    public Player(String name) {
        this.name = name;
    }

    // Gets the name of the player
    public String getName() {
        return name;
    }

    // Sets the player's name
    public void setName(String name) {
        this.name = name;
    }

    // Gets the player's store where they collect stones
    // Returns: the player's store
    public Store getStore() {
        return store;
    }

    // Gets the count of the number of stones in the player's store
    // Returns: the count of stones in the player's store
    public int getStoreCount() {
        return store.getTotalStones();
    }

    // Sets the player's store
    public void setStore(Store store) {
        this.store = store;
    }
}
