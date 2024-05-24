package src.Exception;

public class CantPlantLilypadException extends Exception{
    public CantPlantLilypadException() {
        super("Tidak bisa menanam Lilypad di atas Lilypad!");
    }
}
