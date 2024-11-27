package models;

// Kelas Admin, turunan dari Akun, digunakan untuk mengelola data admin
public class Admin extends Akun {

    // Konstruktor untuk inisialisasi Admin dengan ID, username, dan password
    public Admin(String id, String username, String password) {
        super(id, username, password);
    }

    // Implementasi metode viewMenu khusus untuk Admin
    @Override
    public void viewMenu() {
        System.out.println("Menu Admin");
    }
}
