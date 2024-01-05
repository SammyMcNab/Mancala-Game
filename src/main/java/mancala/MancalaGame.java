package mancala;
import java.util.ArrayList;
import java.util.Arrays;

public class MancalaGame{
    private Board board = new Board();
    private Player currentPlayer;
    private Player p1;
    private Player p2;
    
    public void setPlayers(Player onePlayer, Player twoPlayer){
        this.p1 = onePlayer;

        this.currentPlayer = this.p1;

        this.p2 = twoPlayer;
    }

    public void setCurrentPlayer(Player p){
        this.currentPlayer = p;
    }

    public void setBoard(Board b){
        this.board = b;
    }
    
    public int getNumStones(int pitNum) throws PitNotFoundException{
        return this.board.getPits().get(pitNum-1).getStoneCount();
    }

    public ArrayList<Player> getPlayers(){
        return new ArrayList<Player>(Arrays.asList(p1, p2));
    }  

    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }

    public Board getBoard(){
        return this.board;
    }

    public int getStoreCount(Player player) throws NoSuchPlayerException{

        if(board.getStores().get(1).getOwner() == player){

            return board.getStores().get(1).getTotalStones();
            
        } else if(board.getStores().get(0).getOwner() == player){

            return board.getStores().get(0).getTotalStones();

        }  else {
            throw new NoSuchPlayerException();
        }
    }

    public Player getWinner() throws GameNotOverException{

        int t1 = p1.getStoreCount();
        int t2 = p2.getStoreCount();

        if(t1>t2){ 
            return p1; 
        } else { 
            return p2; 
        }
    }

    public int move(int startPit) throws InvalidMoveException{
        this.board.moveStones(startPit, currentPlayer);
        return 0;
    }

    public boolean isGameOver(){
        try {
            if(board.isSideEmpty(1) && board.isSideEmpty(8)){
                return true;
            }
        } catch (PitNotFoundException p) {
        }
        return false;
    }

    
    public void startNewGame(){
        
        setBoard(new Board());

        board.setUpPits();

        board.setUpStores();

        board.registerPlayers(p1, p2);

        board.initializeBoard();
    }

    public String toString(){
        //player 2 name
        String gameStr = "\t\t\t\t" + p2.toString() + "\n";

        //top row of pits
        gameStr += String.format("%-15s%15s%15s%15s%15s%15s%15s\n", "Pit #", "12", "11", "10", "9", "8","7");
        //stores and pits from board
        gameStr += board.toString();

        gameStr += String.format("%-15s%15s%15s%15s%15s%15s%15s\n", "Pit #", "1", "2", "3", "4", "5","6");
        gameStr += "\t\t\t\t"+p1.toString()+"\n";

        return gameStr;
    }
}