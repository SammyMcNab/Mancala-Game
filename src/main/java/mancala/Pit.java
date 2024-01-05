package mancala;

public class Pit {

    private int stoneNum = 0;

    public int removeStones(){
        int num = this.stoneNum;
        this.stoneNum = 0;
        return num;
    }

    public void addStone(){
        this.stoneNum++;
    }
    
    public int getStoneCount(){
        return this.stoneNum;
    }

    public String toString(){
        return String.format("%15s", "(" + stoneNum + ")");
    }
}