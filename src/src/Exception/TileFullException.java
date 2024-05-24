package src.Exception;

public class TileFullException extends Exception{
    public TileFullException() {
        super("Tile sudah ditempati!");
    }
}
