package payments;

import java.util.Scanner;

public class Bank extends Pembayaran {
    private String namaBank;       // Menyimpan nama bank untuk pembayaran
    private String nomorRekening;  // Menyimpan nomor rekening pengguna

    // Metode untuk memproses pembayaran melalui bank
    @Override
    public String prosesPembayaran(Scanner scanner) {   
        // Memverifikasi apakah pembayaran valid
        if (!verifikasiPembayaran()) {
            return "Pembayaran gagal. Total pembayaran tidak valid.";  // Jika pembayaran tidak valid, kembalikan pesan gagal
        }

        System.out.println("\n=== Pembayaran via Bank ===");
        
        // Meminta pengguna untuk memasukkan nama bank dan nomor rekening
        System.out.print("Masukkan nama bank: ");
        namaBank = scanner.nextLine();  
        
        System.out.print("Masukkan nomor rekening Anda: ");
        nomorRekening = scanner.nextLine();  

        System.out.println("Mengirim permintaan ke bank...");
        
        try {
            Thread.sleep(2000); // Mensimulasikan waktu yang dibutuhkan untuk memproses pembayaran
        } catch (InterruptedException e) {
            return "Terjadi kesalahan saat memproses pembayaran.";  // Menangani kesalahan jika ada gangguan saat tidur
        }

        // Jika pembayaran berhasil, tampilkan informasi pembayaran
        return "Pembayaran via Bank berhasil! Nama Bank: " + namaBank + ", Rekening: " + nomorRekening;
    }

    // Mengembalikan jenis pembayaran yaitu Bank dalam bentuk string
    @Override
    public String toString() {
        return "Bank";  // Menampilkan tipe pembayaran "Bank"
    }
}
