package src.Exception;

public class NoPlantToDigException extends Exception{
    public NoPlantToDigException(int x, int y) {
        super("Tidak ada tanaman yang bisa digali dari (" + x + ", " + y + ") !");
    }
}
