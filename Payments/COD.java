package payments;

import java.util.Scanner;

public class COD extends Pembayaran {
    private String alamatPengiriman;  // Menyimpan alamat pengiriman untuk metode COD

    // Metode untuk memproses pembayaran melalui COD (Cash on Delivery)
    @Override
    public String prosesPembayaran(Scanner scanner) {
        // Memverifikasi apakah pembayaran valid
        if (!verifikasiPembayaran()) {
            return "Pembayaran gagal. Total pembayaran tidak valid.";  // Jika pembayaran tidak valid, kembalikan pesan gagal
        }

        System.out.println("\n=== Pembayaran via COD ===");
        
        // Meminta pengguna untuk memasukkan alamat pengiriman
        System.out.print("Masukkan alamat pengiriman: ");
        alamatPengiriman = scanner.nextLine();

        System.out.println("Pesanan Anda akan dikirim ke alamat berikut:");
        System.out.println(alamatPengiriman);  // Menampilkan alamat pengiriman

        System.out.println("Harap siapkan uang tunai sejumlah: Rp " + total);  // Memberitahukan jumlah uang yang perlu disiapkan

        return "Pesanan dengan metode COD berhasil diproses!";  // Menyatakan bahwa pesanan berhasil diproses
    }

    // Mengembalikan jenis pembayaran yaitu COD dalam bentuk string
    @Override
    public String toString() {
        return "COD";  // Menampilkan tipe pembayaran "COD"
    }
}
