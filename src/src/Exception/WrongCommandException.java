package src.Exception;

public class WrongCommandException extends Exception{
    public WrongCommandException() {
        super("""
              Perintah salah! Masukkan kembali perintah kembali!
              Gunakan "Help" untuk melihat command yang tersedia""");
    }
}
