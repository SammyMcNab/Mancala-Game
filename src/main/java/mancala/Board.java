package mancala;
import java.util.ArrayList;
import java.util.Arrays;

public class Board{
    private ArrayList<Pit> pits = new ArrayList<Pit>();
    private Player currentPlayer;
    private Player p1;
    private Player p2;
    private Store store1;
    private Store store2;
    private boolean freeTurn = false;

    public Board(){

        setUpPits();

        setUpStores();
    }

    public void setCurrentPlayer(Player player){

        this.currentPlayer = player;
    }

    public boolean freeTurn(){

        return freeTurn;
    }

    public void setFreeturn(boolean bool){

        freeTurn = bool;
    }

    public ArrayList<Pit> getPits(){

        return this.pits;
    }

    public void setUpPits(){
        for(int i = 0; i<12; i++) {

            this.pits.add(new Pit());
        }
    }

    public ArrayList<Store> getStores() {

        return new ArrayList<>(Arrays.asList(store1, store2));

    }

    public void setUpStores(){

        store1 = new Store();

        store2 = new Store();

    }

    public void registerPlayers(Player one, Player two){
        this.p1 = one;

        this.p2 = two;

        store1.setOwner(this.p1);

        this.p1.setStore(store1);

        store2.setOwner(this.p2);

        this.p2.setStore(store2);
    }

    public void initializeBoard(){
        for(Pit pit :pits){
            for(int i = 0; i<4; i++){ 
                pit.addStone(); }
        }
    }

    public int distributeStones(int start) throws PitNotFoundException{
        if(start >= 1 && start <= 12) {
 
            int totalStones = pits.get(start-1).getStoneCount();
            int remaingingStones = totalStones;
            int position = start-1; // for array indexing
            pits.get(position).removeStones();

            //case of first drop in a store
            if(position == 11) {
                position = -22;

            } else if (position == 5) {
                position = -11;

            } else {
                position++;
            }

            while(remaingingStones > 0) {

                if(position == 11) {

                        pits.get(position).addStone();
                        remaingingStones = remaingingStones-1;
                        if(remaingingStones == 0){
                            totalStones += captureStones(position);
                            return totalStones;
                        }
                        position = -22;

                } else if(position == 5) {

                        pits.get(position).addStone();
                        remaingingStones = remaingingStones-1;
                        if(remaingingStones == 0){
                            totalStones += captureStones(position);
                            return totalStones;
                        }
                        position = -11;

                } else if(position == -11) {  //case of landing in p1 store
                        if(store1.getOwner() == currentPlayer){
                        store1.addStones(1);
                        remaingingStones = remaingingStones-1;
                            if(remaingingStones == 0){
                                //free turn for ending in store
                                setFreeturn(true);
                            }
                        }
                        position = 6; //if not p1 than skip the store and go to first position of the second row

                } else if (position == -22) { //case of landing in p2 store

                        if(store2.getOwner() == currentPlayer){
                        store2.addStones(1);
                        remaingingStones = remaingingStones-1;

                            if(remaingingStones == 0){
                                //free turn for ending in store
                                setFreeturn(true);
                            }
                        }
                        position = 0; //if not p2 than simply skip the store and go back to first pit
                } else {

                    pits.get(position).addStone();
                    remaingingStones = remaingingStones-1;

                    if(remaingingStones == 0){
                        totalStones += captureStones(position);
                        return totalStones;
                    }
                    position++;
                }
            } 
            return totalStones;
        } else {

            throw new PitNotFoundException();
        }
    }

    public void resetBoard(){
        store1.emptyStore();

        store2.emptyStore();

        for(Pit pit :pits){

            pit.removeStones();
        }

        initializeBoard();
    }
    
    public int moveStones(int startPit, Player player)throws InvalidMoveException{
        currentPlayer = player;
        //make sure each player starts on correct side
        if((startPit > 6 && startPit <= 12 && currentPlayer == p2)
        ||(startPit >= 1 && startPit <= 6 && currentPlayer == p1)) {
            //are there are stones in the pit?
            if(pits.get(startPit-1).getStoneCount() == 0){ 
                throw new InvalidMoveException(); 
            }

            try {
                return distributeStones(startPit);
            } catch (PitNotFoundException pitNotFound) {
                
            }
        }

        throw new InvalidMoveException();
    }

    public int captureStones(int stoppingPoint) throws PitNotFoundException{
        if(stoppingPoint >= 0 && stoppingPoint <= 11){

            int stones = 0;

            if(pits.get(stoppingPoint).getStoneCount() == 1){

                if(currentPlayer == p1){
                    int counter = 11;
                    for(int i = 0; i < 6; i++){
                        if(stoppingPoint == i){
                            //store counter
                            stones = pits.get(stoppingPoint).getStoneCount() + pits.get(counter).getStoneCount();

                            pits.get(stoppingPoint).removeStones();
                            pits.get(counter).removeStones();

                            p1.getStore().addStones(stones);

                            return stones;
                        }
                        counter--;
                    }
                } else {
                    int counter = 0;
                    for(int i = 11; i > 5; i--){
                        if(stoppingPoint == i){
                            //take from counter
                            stones = pits.get(stoppingPoint).getStoneCount() + pits.get(counter).getStoneCount();

                            pits.get(stoppingPoint).removeStones();
                            pits.get(counter).removeStones();

                            p2.getStore().addStones(stones);

                            return stones;
                        }
                        counter++;
                    }
                }
            }
            return stones;
        } else {
            throw new PitNotFoundException();
        }
    }

    public int getNumStones(int pitNum) throws PitNotFoundException{
        if(pitNum >= 1 && pitNum <= 12){
            return pits.get(pitNum-1).getStoneCount();
        } else {
            throw new PitNotFoundException();
        }
    }

    public boolean isSideEmpty(int pitNum) throws PitNotFoundException{
        boolean empty = true;
        if((pitNum > 6 && pitNum <= 12)){
            for(int i = 6; i < 12; i++){
                if(pits.get(i).getStoneCount() != 0){
                    empty = false;
                    break;
                }
            }
        } else if((pitNum >= 1 && pitNum <= 6)){
            for(int i = 0; i < 6; i++) {

                if(pits.get(i).getStoneCount() != 0){
                     
                    empty = false;
                    break;
                }
            }
        } else {
            throw new PitNotFoundException();
        }

        return empty;
    }

    public String toString(){
        //store2
        String boardStr = store2.toString();
        try {
            //p2 pits
            for(int i = 11; i > 5; i--){
                boardStr += pits.get(i).toString();
            }
             //store1
            boardStr += store1.toString() + "\n";
            boardStr += "|Stones     |";
            //p1 pits
            for(int i = 0; i < 6; i++){
                boardStr += pits.get(i).toString();
            }
            boardStr += "| Stones    |\n";
        } catch (Exception e) {
            // never throws exception
        }
        return boardStr;
    }
}