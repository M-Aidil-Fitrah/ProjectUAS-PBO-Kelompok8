package models;

import java.io.*;
import java.util.*;
import payments.*;

// // Customer merupakan turunan dari class Akun
public class Customer extends Akun {
    private Keranjang keranjang;           // Keranjang belanja pelanggan
    private List<Invoice> invoiceSelesai;  // Daftar invoice yang telah selesai

    // Konstruktor untuk inisialisasi data Customer
    public Customer(String id, String username, String password) {
        super(id, username, password); // Memanggil konstruktor dari kelas Akun
        this.keranjang = new Keranjang(); // Inisialisasi keranjang
        this.invoiceSelesai = new ArrayList<>(); // Inisialisasi daftar invoice selesai
        muatRiwayatDariFile(); // Memuat riwayat transaksi dari file
    }

    // Override metode viewMenu untuk menampilkan menu khusus pelanggan
    @Override
    public void viewMenu() {
        System.out.println("Menu Customer");
    }

    // Menambahkan barang ke keranjang dengan jumlah tertentu
    public void tambahKeKeranjang(Barang barang, int jumlah) {
        barang.setJumlahCheckout(jumlah); // Mengatur jumlah barang untuk checkout
        keranjang.tambahBarang(barang, jumlah); // Menambah barang ke keranjang
        System.out.println(barang.getNama() + " telah ditambahkan ke keranjang."); // Informasi kepada pelanggan
    }

    // Getter untuk keranjang belanja
    public Keranjang getKeranjang() {
        return keranjang;
    }

    // Getter untuk daftar invoice selesai
    public List<Invoice> getInvoiceSelesai() {
        return invoiceSelesai;
    }

    // Memuat riwayat transaksi selesai dari file
    private void muatRiwayatDariFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/transactions.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) { // Membaca file baris per baris
                String[] parts = line.split(","); // Memisahkan data berdasarkan koma
                if (parts.length >= 5) { // Memastikan data cukup untuk diproses
                    String idTransaksi = parts[0].split("=")[1].trim(); // Mendapatkan ID transaksi
                    String status = parts[3].split("=")[1].trim(); // Mendapatkan status transaksi
                    String pembayaranStr = parts[4].split("=")[1].trim(); // Mendapatkan metode pembayaran

                    if ("SELESAI".equals(status)) { // Hanya memproses transaksi yang selesai
                        int total = Integer.parseInt(parts[2].split("=")[1].trim()); // Mendapatkan total transaksi

                        Pembayaran pembayaran = null;
                        // Menentukan jenis pembayaran berdasarkan string
                        switch (pembayaranStr) {
                            case "Bank":
                                pembayaran = new Bank();
                                break;
                            case "QRIS":
                                pembayaran = new QRIS();
                                break;
                            case "COD":
                                pembayaran = new COD();
                                break;
                        }

                        if (pembayaran != null) {
                            // Membuat objek Invoice dan menambahkannya ke daftar invoice selesai
                            Invoice invoice = new Invoice(idTransaksi, total, status, pembayaran);
                            this.invoiceSelesai.add(invoice);
                        }
                    }
                }
            }
        } catch (IOException e) {
            // Menangani error saat membaca file
            System.out.println("Error saat membaca file transaksi: " + e.getMessage());
        }
    }

    // Menampilkan riwayat transaksi selesai
    public void lihatRiwayat() {
        System.out.println("\t Riwayat Belanja ");

        if (invoiceSelesai.isEmpty()) { // Mengecek apakah tidak ada transaksi selesai
            System.out.println("Belum ada transaksi yang selesai.");
            return;
        }

        // Iterasi melalui daftar invoice selesai dan menampilkan informasi
        for (Invoice invoice : invoiceSelesai) {
            System.out.println("=================================");
            System.out.println("ID Transaksi : " + invoice.getIdTransaksi());
            System.out.println("Total        : " + invoice.getTotal());
            System.out.println("Status       : " + invoice.getStatus());
            System.out.println("Pembayaran   : " + invoice.getPembayaran().getClass().getSimpleName());

            try (BufferedReader reader = new BufferedReader(new FileReader("data/transactions.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) { // Membaca file baris per baris
                    if (line.contains(invoice.getIdTransaksi())) { // Mencocokkan ID transaksi
                        String barang = line.substring(line.indexOf("barang=") + 7); // Mendapatkan bagian barang
                        String[] daftarBarang = barang.split(","); // Memisahkan daftar barang
                        System.out.print("Daftar Barang: ");
                        for (String b : daftarBarang) {
                            String[] partsBarang = b.trim().split(" \\(x");
                            String namaBarang = partsBarang[0]; // Nama barang
                            int jumlahBarang = Integer.parseInt(partsBarang[1].replace(")", "")); // Jumlah barang
                            System.out.print(namaBarang + " (x" + jumlahBarang + "), "); // Menampilkan barang
                        }
                        System.out.println(); // Baris baru untuk daftar barang
                        break;
                    }
                }
            } catch (IOException e) {
                // Menangani error saat membaca file untuk daftar barang
                System.out.println("Error saat membaca file transaksi untuk barang: " + e.getMessage());
            }

            System.out.println("=================================");
        }
    }
}
