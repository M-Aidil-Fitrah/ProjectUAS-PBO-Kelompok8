package models;

// Kelas abstrak Pembayaran dengan atribut ID
public abstract class Pembayaran {
    // Atribut yang dilindungi (protected) untuk menyimpan ID pembayaran
    protected String id;

    // Konstruktor untuk menginisialisasi ID pembayaran
    public Pembayaran(String id) {
        this.id = id;
    }
   
    // Metode abstrak untuk memproses pembayaran
    public abstract void prosesPembayaran();
}

    
