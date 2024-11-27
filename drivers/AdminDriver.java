package drivers;

// Import paket dan class terkait
import models.*;
import payments.*;

import java.io.*;
import java.util.*;

// AdminDriver merupakan turunan dari class Driver
public class AdminDriver extends Driver {
    private Admin admin;  // Objek Admin untuk menyimpan data admin
    private ListBarang listBarang;  // Objek ListBarang untuk mengelola daftar barang
    private ArrayList<Transaksi> listTransaksi;  // Daftar transaksi
    private List<Invoice> invoiceSelesai = new ArrayList<>();  // Daftar invoice yang selesai

    // Konstruktor untuk inisialisasi objek AdminDriver
    public AdminDriver(Admin admin, ListBarang listBarang) {
        this.admin = admin;
        this.listBarang = listBarang;
        this.listTransaksi = new ArrayList<>();
    }

    // Implementasi metode menu yang ditampilkan kepada admin
    @Override
    public void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) { // Loop utama untuk menampilkan menu
            System.out.println("\n=== Menu Admin ===");
            System.out.println("Admin: " + admin.getUsername());
            System.out.println("1. Tambah Barang");
            System.out.println("2. Hapus Barang");
            System.out.println("3. Edit Barang");
            System.out.println("4. Lihat Barang");
            System.out.println("5. Lihat Transaksi");
            System.out.println("6. Terima Transaksi"); 
            System.out.println("7. Keluar");
            System.out.print("Pilih opsi: ");
            int opsi = Integer.parseInt(scanner.nextLine()); // Membaca pilihan menu

            switch (opsi) { // Switch case untuk menangani pilihan menu
                case 1:
                    listBarang.tambahBarang(scanner); // Tambah barang
                    break;
                case 2:
                    listBarang.hapusBarang(scanner); // Hapus barang
                    break;
                case 3:
                    listBarang.editBarang(scanner); // Edit barang
                    break;
                case 4:
                    listBarang.lihatBarang(); // Lihat daftar barang
                    break;
                case 5:
                    lihatTransaksi(); // Lihat daftar transaksi
                    break;
                case 6:
                    terimaTransaksi(scanner); // Terima transaksi
                    break;
                case 7:
                    return; // Keluar dari menu
                default:
                    System.out.println("Pilihan tidak valid!"); // Pilihan tidak valid
            }
        }
    }

    // Metode untuk memuat daftar transaksi dari file
    private void muatTransaksiDariFile() {
        File file = new File("data/transactions.txt"); // File tempat transaksi disimpan
        listTransaksi.clear(); // Membersihkan daftar transaksi sebelumnya

        if (!file.exists()) { // Jika file tidak ditemukan
            return;
        }

        // Membaca file menggunakan BufferedReader
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) { // Baca setiap baris dari file
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    // Parsing data transaksi
                    String idTransaksi = parts[0].split("=")[1].trim();
                    String username = parts[1].split("=")[1].trim();
                    int total = Integer.parseInt(parts[2].split("=")[1].trim());
                    String status = parts[3].split("=")[1].trim();
                    String pembayaranStr = parts[4].split("=")[1].trim();

                    // Menentukan jenis pembayaran
                    Pembayaran pembayaran = null;
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
                        default:
                            System.out.println("Pembayaran tidak dikenal: " + pembayaranStr);
                            break;
                    }

                    // Parsing barang dalam transaksi
                    List<Barang> barangList = new ArrayList<>();
                    for (int i = 5; i < parts.length; i++) {
                        String barangStr = parts[i].trim();
                        String namaBarang = barangStr.split(" \\(x")[0];
                        int jumlahBarang = Integer.parseInt(barangStr.split(" \\(x")[1].replace(")", ""));
                        Barang barang = new Barang(namaBarang, jumlahBarang); // Membuat objek barang
                        barangList.add(barang);
                    }
                    // Membuat objek Transaksi dan menambahkannya ke daftar transaksi
                    Transaksi transaksi = new Transaksi(idTransaksi, username, total, status, pembayaran, barangList);
                    listTransaksi.add(transaksi);
                }
            }
        } catch (IOException e) { // Menangani error saat membaca file
            System.out.println("Error saat membaca file transaksi: " + e.getMessage());
        }
    }

    // Metode untuk melihat daftar transaksi
    public void lihatTransaksi() {
        muatTransaksiDariFile(); // Memuat transaksi terbaru dari file
        if (listTransaksi.isEmpty()) { // Jika tidak ada transaksi
            System.out.println("Belum ada transaksi.");
        } else {
            System.out.println("\t Daftar Transaksi ");
            for (Transaksi transaksi : listTransaksi) { // Menampilkan setiap transaksi
                System.out.println("=================================");
                System.out.println(transaksi.formatTransaksi()); // Format transaksi
                System.out.println("=================================");
            }
        }
    }

    // Metode untuk menerima transaksi berdasarkan ID
    public void terimaTransaksi(Scanner scanner) {
        lihatTransaksi(); // Menampilkan daftar transaksi

        if (listTransaksi.isEmpty()) { // Jika tidak ada transaksi yang tersedia
            System.out.println("Tidak ada transaksi yang dapat diterima.");
            return;
        }

        System.out.print("\nMasukkan ID Transaksi yang ingin diterima: ");
        String idTransaksi = scanner.nextLine(); // Membaca ID transaksi dari pengguna
    
        for (Transaksi transaksi : listTransaksi) { // Mencari transaksi berdasarkan ID
            if (transaksi.getIdTransaksi().equals(idTransaksi)) {
                if (transaksi.getStatus().equals("PENDING")) { // Jika status transaksi adalah "PENDING"
                    transaksi.setStatus("SELESAI"); // Mengubah status menjadi "SELESAI"
                    transaksi.simpanTransaksiKeFile(); // Menyimpan perubahan ke file
    
                    // Membuat invoice untuk transaksi yang selesai
                    Invoice invoice = new Invoice(transaksi.getIdTransaksi(), transaksi.getTotal(), "SELESAI", transaksi.getPembayaran());
                    invoiceSelesai.add(invoice);
    
                    System.out.println("Transaksi ID " + idTransaksi + " telah diterima dan status diperbarui menjadi SELESAI.");
                } else {
                    // Jika transaksi sudah selesai atau memiliki status lain
                    System.out.println("Transaksi ID " + idTransaksi + " sudah dalam status " + transaksi.getStatus() + ".");
                }
                return;
            }
        }
        // Jika transaksi dengan ID yang dimasukkan tidak ditemukan
        System.out.println("Transaksi dengan ID " + idTransaksi + " tidak ditemukan.");
    }
}
