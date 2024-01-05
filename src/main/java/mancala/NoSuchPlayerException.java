package mancala;

public class NoSuchPlayerException extends Exception{
    public NoSuchPlayerException(){
        super("Invalid Player: No such player");
    }
}