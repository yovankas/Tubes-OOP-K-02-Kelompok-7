package src.Exception;

public class CooldownNotDoneException extends Exception{
    public CooldownNotDoneException() {
        super("Waktu cooldown belum selesai.");
    }
}
