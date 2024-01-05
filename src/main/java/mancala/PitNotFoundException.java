package mancala;

public class PitNotFoundException extends Exception{
    public PitNotFoundException(){
        super("Invalid pit: Pit not found");
    }
}