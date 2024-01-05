public class NoSuchPlayerException extends Exception {
    public NoSuchPlayerException() {
        super("Player not found: The specified player does not exist.");
    }
}
