package models;

import java.util.Scanner;
import payments.Pembayaran;

// Kelas Invoice merepresentasikan sebuah tagihan yang mencatat informasi transaksi
public class Invoice {
    private String idTransaksi;        // ID unik untuk transaksi
    private int total;                 // Total biaya transaksi
    private String status;             // Status transaksi 
    private Pembayaran pembayaran;     // Objek pembayaran yang digunakan untuk transaksi

    // Konstruktor untuk inisialisasi data Invoice
    public Invoice(String idTransaksi, int total, String status, Pembayaran pembayaran) {
        this.idTransaksi = idTransaksi; // Mengatur ID transaksi
        this.total = total; // Mengatur total transaksi
        this.status = status; // Mengatur status transaksi
        this.pembayaran = pembayaran; // Mengatur metode pembayaran
    }

    // Getter untuk ID transaksi
    public String getIdTransaksi() {
        return idTransaksi;
    }

    // Getter untuk total transaksi
    public int getTotal() {
        return total;
    }

    // Getter untuk status transaksi
    public String getStatus() {
        return status;
    }

    // Setter untuk status transaksi
    public void setStatus(String status) {
        this.status = status;
    }

    // Getter untuk metode pembayaran
    public Pembayaran getPembayaran() {
        return pembayaran;
    }

    // Setter untuk metode pembayaran
    public void setPembayaran(Pembayaran pembayaran) {
        this.pembayaran = pembayaran;
    }

    // Metode untuk memproses pembayaran menggunakan objek pembayaran yang terkait
    public String prosesPembayaran(Scanner scanner) {
        return pembayaran.prosesPembayaran(scanner); // Memanggil metode prosesPembayaran pada objek pembayaran
    }

    // Override metode toString untuk menampilkan informasi Invoice dalam bentuk string
    @Override
    public String toString() {
        return "Invoice{" +
                "idTransaksi='" + idTransaksi + '\'' + // Menampilkan ID transaksi
                ", total=" + total + // Menampilkan total transaksi
                ", status='" + status + '\'' + // Menampilkan status transaksi
                ", pembayaran=" + pembayaran.getClass().getSimpleName() +  // Menampilkan nama kelas metode pembayaran
                '}';
    }
}
