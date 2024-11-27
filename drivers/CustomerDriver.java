package drivers;

import models.*; 
import java.io.*; 
import java.util.Scanner; 

// CustomerDriver merupakan turunan dari class Driver
public class CustomerDriver extends Driver {
    private Customer customer;     // Objek untuk menyimpan informasi pelanggan
    private ListBarang listBarang; // Objek untuk mengelola daftar barang

    // Konstruktor untuk menginisialisasi CustomerDriver dengan objek Customer dan ListBarang
    public CustomerDriver(Customer customer, ListBarang listBarang) {
        this.customer = customer;
        this.listBarang = listBarang;
    }

    @Override
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        
        // Perulangan untuk menampilkan menu pelanggan
        while (true) {
            System.out.println("\n=== Menu Customer ===");
            System.out.println("1. Lihat Barang");
            System.out.println("2. Tambah ke Keranjang");
            System.out.println("3. Checkout");
            System.out.println("4. Lihat Status Transaksi");
            System.out.println("5. Lihat Riwayat Belanja");
            System.out.println("6. Keluar");
            System.out.print("Pilih opsi: ");
            int opsi = Integer.parseInt(scanner.nextLine());

            // Menangani pilihan menu berdasarkan input pengguna
            switch (opsi) {
                case 1:
                    listBarang.lihatBarang(); // Menampilkan daftar barang
                    break;
                case 2:
                    tambahKeKeranjang(scanner); // Menambahkan barang ke keranjang
                    break;
                case 3:
                    Transaksi.checkout(customer, listBarang, scanner); // Melakukan proses checkout
                    break;
                case 4:
                    lihatStatusTransaksi(); // Menampilkan status transaksi pelanggan
                    break;
                case 5:
                    customer.lihatRiwayat(); // Menampilkan riwayat belanja pelanggan
                    break;
                case 6:
                    return; // Keluar dari menu
                default:
                    System.out.println("Pilihan tidak valid!"); // Validasi input
            }
        }
    } 

    // Metode untuk menambahkan barang ke keranjang belanja
    private void tambahKeKeranjang(Scanner scanner) {
        listBarang.lihatBarang(); // Menampilkan daftar barang
        System.out.println("==========================================");
        System.out.println("\nTambahkan barang ke keranjang");
        System.out.print("Masukkan ID barang: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Masukkan jumlah: ");
        int jumlah = Integer.parseInt(scanner.nextLine());
    
        Barang barang = listBarang.getBarangById(id); // Mencari barang berdasarkan ID
        if (barang != null) {
            if (jumlah > barang.getStok()) {
                // Validasi jika jumlah melebihi stok barang
                System.out.println("Jumlah yang ingin ditambahkan melebihi stok yang tersedia. Stok yang tersedia: " + barang.getStok());
            } else {
                customer.tambahKeKeranjang(barang, jumlah); // Menambahkan barang ke keranjang pelanggan
            }
        } else {
            System.out.println("Barang dengan ID tersebut tidak ditemukan."); // Validasi jika barang tidak ditemukan
        }
    }    

    // Metode untuk menampilkan status transaksi pelanggan
    public void lihatStatusTransaksi() {
        File file = new File("data/transactions.txt"); // File yang menyimpan data transaksi
        if (!file.exists()) {
            // Jika file tidak ditemukan
            System.out.println("Belum ada transaksi.");
            return;
        }
    
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean found = false;
            String usernameDicari = "username=" + customer.getUsername(); // Mencari transaksi berdasarkan username pelanggan
            
            System.out.println("\t Daftar Transaksi ");

            // Membaca file transaksi baris per baris
            while ((line = reader.readLine()) != null) {
                if (line.contains(usernameDicari)) {
                    found = true;
                    String[] parts = line.split(","); // Memisahkan data transaksi
                    String idTransaksi = parts[0].split("=")[1].trim(); // ID transaksi
                    String total = parts[2].split("=")[1].trim(); // Total harga
                    String status = parts[3].split("=")[1].trim(); // Status transaksi
                    String pembayaran = parts[4].split("=")[1].trim(); // Metode pembayaran
                    String barang = line.substring(line.indexOf("barang=") + 7); // Daftar barang dalam transaksi
                    
                    // Menampilkan detail transaksi
                    System.out.println("=================================");
                    System.out.println("ID Transaksi : " + idTransaksi);
                    System.out.println("Total        : " + total);
                    System.out.println("Status       : " + status);
                    System.out.println("Pembayaran   : " + pembayaran);
                    
                    // Memproses dan menampilkan daftar barang
                    String[] daftarBarang = barang.split(",");
                    System.out.print("Daftar Barang: ");
                    for (String b : daftarBarang) {
                        String[] partsBarang = b.trim().split(" \\(x");
                        String namaBarang = partsBarang[0];
                        int jumlahBarang = Integer.parseInt(partsBarang[1].replace(")", ""));
                        System.out.print(namaBarang + " (x" + jumlahBarang + "), ");
                    }
                    System.out.println(); 
                    System.out.println("=================================");
                }
            }
        
            if (!found) {
                // Jika tidak ada transaksi untuk pelanggan
                System.out.println("Tidak ada transaksi ditemukan untuk pengguna ini.");
            }
        } catch (IOException e) {
            // Penanganan kesalahan saat membaca file
            System.out.println("Error saat membaca file transaksi: " + e.getMessage());
        }
    }
}
