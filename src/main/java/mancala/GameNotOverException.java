package mancala;

public class GameNotOverException extends Exception{
    public GameNotOverException(){
        super("The game continues!");
    }
}