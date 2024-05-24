package src.Exception;

public class PlantAlreadyInLilypadException extends Exception{
    public PlantAlreadyInLilypadException() {
        super("Sudah ada tanaman di Lilypad!");
    }
}
