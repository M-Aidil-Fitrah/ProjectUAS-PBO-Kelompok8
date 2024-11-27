package payments;

import java.util.Scanner;

// Kelas abstrak yang menjadi dasar untuk berbagai metode pembayaran
public abstract class Pembayaran {
    protected int total; // Menyimpan total pembayaran yang harus dibayar

    // Setter untuk mengatur total pembayaran
    public void setTotal(int total) {
        this.total = total;
    }

    // Metode abstrak yang harus diimplementasikan oleh kelas turunan untuk memproses pembayaran
    public abstract String prosesPembayaran(Scanner scanner);

    // Metode untuk memverifikasi apakah total pembayaran lebih dari 0
    public boolean verifikasiPembayaran() {
        return total > 0; // Mengembalikan true jika total pembayaran valid (lebih dari 0)
    }

    // Metode toString untuk menampilkan deskripsi umum dari pembayaran
    @Override
    public String toString() {
        return "Pembayaran umum"; // Mengembalikan string yang menjelaskan bahwa ini adalah pembayaran umum
    }
}
