package src.Exception;

public class NoLilypadException extends Exception{
    public NoLilypadException() {
        super("Tidak bisa tanam, tidak ada Lilypad!");
    }
}
