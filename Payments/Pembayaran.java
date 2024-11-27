package payments;

import java.util.Scanner;

// Kelas abstrak dasar untuk metode pembayaran
public abstract class Pembayaran {
    protected int total; // Total pembayaran

    // Setter untuk total pembayaran
    public void setTotal(int total) {
        this.total = total;
    }

    // Metode abstrak untuk memproses pembayaran
    public abstract String prosesPembayaran(Scanner scanner);

    // Verifikasi apakah total pembayaran lebih dari 0
    public boolean verifikasiPembayaran() {
        return total > 0;
    }

    // Deskripsi umum pembayaran
    @Override
    public String toString() {
        return "Pembayaran umum";
    }
}
