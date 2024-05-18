package src.Exception;

public class PlantAlreadyInDeckException extends Exception{
    public PlantAlreadyInDeckException() {
        super("Jenis tanaman sudah ada di dalam Deck!");
    }
}
