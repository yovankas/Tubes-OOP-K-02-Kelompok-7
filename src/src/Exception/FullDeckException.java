package src.Exception;

public class FullDeckException extends Exception{
    public FullDeckException() {
        super("Deck sudah full!");
    }
}
