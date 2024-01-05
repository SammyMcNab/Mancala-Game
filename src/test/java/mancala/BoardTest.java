package mancala;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

public class BoardTest {
    private ArrayList<Pit> pits = new ArrayList<Pit>();
    private Board fixture;
    private Player p1, p2;
    private Player currentPlayer;
    private Store store1, store2;
    private boolean freeTurn = false;

    @BeforeEach
    public void setup(){
        fixture = new Board();
        p1 = new Player("Player 1");
        p2 = new Player("Player 2");
        fixture.setUpStores();
        fixture.registerPlayers(p1, p2);
        fixture.setUpPits();
        fixture.initializeBoard();
    }

    private int getPitValue(int pit){
        return fixture.getPits().get(pit-1).getStoneCount();
    }

    private int getStoreValue(int store){
        return fixture.getStores().get(store-1).getTotalStones();
    }


    @Test
    public void allPitsHave4() {
        for(Pit pit : fixture.getPits()) {
            assertEquals(pit.getStoneCount(), 4);
        }
    }

    @Test
    public void testRegisterOfPlayers() {

        fixture.registerPlayers(p1, p2);

        assertEquals(p1, fixture.getStores().get(0).getOwner());
        assertEquals(p2, fixture.getStores().get(1).getOwner());
        assertEquals(fixture.getStores().get(0), p1.getStore());
        assertEquals(fixture.getStores().get(1), p2.getStore());
    }

    @Test 
    public void pitsResetBackTo4() {
        fixture.getPits().get(4).addStone();
        assertEquals(fixture.getPits().get(4).getStoneCount(), 5);
        fixture.resetBoard();
        assertEquals(fixture.getPits().get(4).getStoneCount(), 4);
    }

   @Test 
    public void storesAreEmptyAfterReset() {
        p1.getStore().addStones(4);
        p2.getStore().addStones(6);
        fixture.resetBoard();
        assertEquals(p1.getStore().getTotalStones(), 0);
        assertEquals(p2.getStore().getTotalStones(), 0);
    }
    

   @Test 
    public void distributeStonesTest() throws PitNotFoundException {
        int totalStones = fixture.distributeStones(2);
        assertEquals(totalStones, 4);
    }
   @Test 
    public void sideNotEmpty() throws PitNotFoundException{
        assertFalse(fixture.isSideEmpty(1));
        assertFalse(fixture.isSideEmpty(2));
        assertFalse(fixture.isSideEmpty(6));
        assertFalse(fixture.isSideEmpty(7));
        assertFalse(fixture.isSideEmpty(8));
    }
    @Test 
    public void sideEmpty() throws PitNotFoundException {
        fixture.getPits().get(0).removeStones();
        fixture.getPits().get(1).removeStones();
        fixture.getPits().get(2).removeStones();
        fixture.getPits().get(3).removeStones();
        fixture.getPits().get(4).removeStones();
        fixture.getPits().get(5).removeStones();
        assertTrue(fixture.isSideEmpty(2));
    }
    @Test
    public void numberOfStonesAtPit() throws PitNotFoundException {
        assertEquals(fixture.getNumStones(4), 4);

    }  
    @Test 
    public void stonesCaptured() throws PitNotFoundException{
        int pit = 1;

        fixture.getPits().get(pit).removeStones(); //clear stones so that pit is empty

        fixture.getPits().get(pit).addStone(); //add one since one will land in the pit

        assertEquals(fixture.getPits().get(pit).getStoneCount(), 1); //make sure one stone is in the pit
        fixture.captureStones(pit); //capture the stones on the opposite side
        assertEquals(p1.getStoreCount(), 5); // there should be 5 stones in p1's store 4 from the pit on the other side
                                             //and 1 from the stone landing in the pit
                                             //fails junit test but works in-game??
    }

}