package models;

// Kelas abstrak Akun, sebagai template untuk berbagai jenis akun
public abstract class Akun {
    private String id;        // ID unik untuk akun
    private String username;  // Nama pengguna
    private String password;  // Kata sandi

    // Konstruktor untuk inisialisasi atribut akun
    public Akun(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    // Getter untuk mendapatkan ID akun
    public String getId() {
        return id;
    }

    // Getter untuk mendapatkan username
    public String getUsername() {
        return username;
    }

    // Getter untuk mendapatkan password
    public String getPassword() {
        return password;
    }

    // Metode abstrak untuk menampilkan menu, wajib diimplementasikan oleh subclass
    public abstract void viewMenu();
}
