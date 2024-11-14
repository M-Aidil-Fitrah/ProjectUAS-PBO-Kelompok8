package models;

// Kelas QRIS merupakan turunan dari kelas Pembayaran dan menangani pembayaran melalui metode QRIS
public class QRIS extends Pembayaran {

    // Konstruktor QRIS yang menerima ID pembayaran
    public QRIS(String id) {
        // Memanggil konstruktor superclass (Pembayaran) dengan ID yang diberikan
        super(id);
    }

    // Metode override untuk memproses pembayaran khusus QRIS
    @Override
    public void prosesPembayaran() {
        // Menampilkan pesan bahwa pembayaran melalui QRIS berhasil
        System.out.println("Pembayaran via QRIS berhasil.");
    }
}

 
