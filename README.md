# Tubes-OOP-K-02-Kelompok-7

## Prasyarat
Pastikan Anda telah menginstal JDK (Java Development Kit) di sistem Anda. Anda dapat mengunduh JDK dari [Oracle](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) atau [OpenJDK](https://openjdk.java.net/).

## Langkah-langkah Kompilasi dan Menjalankan Program

1. **Buka terminal atau command prompt** di direktori `src/src/` di dalam proyek Anda. Direktori ini adalah direktori yang berisi file-file `.java` dan subdirektori `Exception`, `Zombies`, `Plants`, `Tiles`, dan `Game`.

2. **Kompilasi semua file Java** dengan menjalankan perintah berikut:
    ```sh
    javac *.java Exception/*.java Zombies/*.java Plants/*.java Tiles/*.java Game/*.java
    ```
    Perintah ini akan mengkompilasi semua file `.java` yang berada di direktori `src/src/` dan subdirektori terkait.

3. **Jalankan program** dengan menjalankan perintah berikut:
    ```sh
    java main
    ```
    Pastikan bahwa kelas `main` adalah entry point dari program Anda dan tidak memiliki ekstensi `.java` saat dijalankan.

## Troubleshooting
- **Error Kompilasi**: Jika ada error saat kompilasi, pastikan semua file `.java` berada di lokasi yang benar dan tidak ada kesalahan sintaks.
- **Class Not Found Exception**: Jika Anda mendapatkan error ini saat menjalankan program, pastikan kelas `main` berada dalam package yang benar (jika ada), dan tidak ada kesalahan penulisan nama file dan nama kelas.

---

