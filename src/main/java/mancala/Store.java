package mancala;

public class Store {
    private Player owner;
    private int numStones = 0;

    public Player getOwner(){
        return this.owner;
    }

    public int getTotalStones(){
        return this.numStones;
    }

    public void addStones(int amount){
        numStones += amount;
    }

    public void setOwner(Player p){
        this.owner = p;
    }

    public int emptyStore(){
        int num = getTotalStones();
        this.numStones = 0;
        return num;
    }

    public String toString(){
        return String.format("|%-11d|", getTotalStones());
    }
}