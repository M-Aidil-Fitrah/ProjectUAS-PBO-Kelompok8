package payments;

import java.util.Random;
import java.util.Scanner;

public class QRIS extends Pembayaran {
    private String kodeQR; // Menyimpan kode QR untuk pembayaran via QRIS

    // Metode untuk memproses pembayaran melalui QRIS
    @Override
    public String prosesPembayaran(Scanner scanner) {
        // Memverifikasi apakah pembayaran valid
        if (!verifikasiPembayaran()) {
            return "Pembayaran gagal. Total pembayaran tidak valid."; // Jika pembayaran tidak valid, kembalikan pesan gagal
        }

        System.out.println("\n=== Pembayaran via QRIS ===");
        
        // Membuat kode QR untuk pembayaran
        kodeQR = generateKodeQR();
        
        // Menampilkan kode QR untuk dipindai
        System.out.println("Kode QR telah dibuat. Silakan pindai kode QR berikut untuk membayar:");
        System.out.println("[ Kode QR: " + kodeQR + " ]");

        System.out.println("Menunggu pembayaran...");
        
        try {
            Thread.sleep(3000); // Mensimulasikan waktu yang dibutuhkan untuk menunggu pembayaran
        } catch (InterruptedException e) {
            return "Terjadi kesalahan saat memproses pembayaran."; // Menangani kesalahan jika ada gangguan saat tidur
        }

        // Jika pembayaran berhasil, tampilkan pesan berhasil
        return "Pembayaran via QRIS berhasil!";
    }

    // Metode untuk menghasilkan kode QR secara acak
    private String generateKodeQR() {
        Random random = new Random();
        return "QR-" + random.nextInt(1000000); // Menghasilkan kode QR yang diawali dengan "QR-" diikuti angka acak
    }

    // Mengembalikan jenis pembayaran yaitu QRIS dalam bentuk string
    @Override
    public String toString() {
        return "QRIS"; // Menampilkan tipe pembayaran "QRIS"
    }
}
