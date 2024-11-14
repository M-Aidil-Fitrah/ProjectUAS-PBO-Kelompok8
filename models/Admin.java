package models;

// Kelas Admin adalah subclass dari kelas Akun, yang mewakili akun dengan peran Admin
public class Admin extends Akun {
       
    // Konstruktor kelas Admin, menerima id, username, dan password sebagai parameter
    public Admin(String id, String username, String password) {
    // Memanggil konstruktor superclass (Akun) untuk menginisialisasi atribut
        super(id, username, password);
    }
    
    // Override metode getRole untuk mengembalikan peran sebagai "Admin"
    @Override
    public String getRole() {
        return "Admin";
    }
}
