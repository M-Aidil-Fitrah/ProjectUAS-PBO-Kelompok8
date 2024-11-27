package models;

import java.io.*;
import java.util.*;

// Kelas ListBarang untuk mengelola daftar barang yang ada dalam sistem
public class ListBarang {
    private List<Barang> barangList = new ArrayList<>(); // Daftar barang yang tersedia

    // Konstruktor untuk inisialisasi dan memuat produk dari file
    public ListBarang() {
        barangList = new ArrayList<>();
        loadProducts(); // Memuat data barang dari file
    }

    // Metode untuk memuat produk dari file
    public void loadProducts() {
        barangList.clear(); // Mengosongkan daftar barang sebelum memuat yang baru
        try (BufferedReader br = new BufferedReader(new FileReader("data/products.txt"))) {
            String line;
            // Membaca setiap baris dalam file dan memisahkannya menjadi atribut
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String nama = parts[1];
                int harga = Integer.parseInt(parts[2]);
                int stok = Integer.parseInt(parts[3]);
                barangList.add(new Barang(id, nama, harga, stok)); // Menambahkan barang ke dalam list
            }
        } catch (IOException e) {
            System.out.println("Error membaca file produk."); // Menangani error jika file tidak bisa dibaca
        }
    }

    // Metode untuk mencari barang berdasarkan ID
    public Barang getBarangById(int id) {
        for (Barang barang : barangList) {
            if (barang.getId() == id) {
                return barang; // Mengembalikan barang yang ditemukan
            }
        }
        return null; // Mengembalikan null jika barang tidak ditemukan
    }

    // Getter untuk mendapatkan daftar barang
    public List<Barang> getBarangList() {
        return barangList;
    }

    // Menambahkan barang baru ke dalam daftar
    public void tambahBarang(Scanner scanner) {
        System.out.println("\t  Tambah Barang ");
        System.out.println("=================================");
        System.out.print("Masukkan ID barang: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Masukkan nama barang: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan harga barang: ");
        int harga = Integer.parseInt(scanner.nextLine());
        System.out.print("Masukkan stok barang: ");
        int stok = Integer.parseInt(scanner.nextLine());
        
        barangList.add(new Barang(id, nama, harga, stok)); // Menambahkan barang ke daftar
        simpanBarangKeFile(); // Menyimpan perubahan ke file
        System.out.println("=================================");
        System.out.println("Barang berhasil ditambahkan.");
    }

    // Menghapus barang dari daftar berdasarkan ID
    public void hapusBarang(Scanner scanner) {
        System.out.println("\t  Hapus Barang ");
        System.out.println("=================================");
        System.out.print("Masukkan ID barang yang ingin dihapus: ");
        int id = Integer.parseInt(scanner.nextLine());
    
        // Menghapus barang berdasarkan ID dan memastikan keberhasilan penghapusan
        boolean removed = barangList.removeIf(barang -> barang.getId() == id);
        if (removed) {
            simpanBarangKeFile(); // Menyimpan perubahan ke file
            System.out.println("=================================");
            System.out.println("Barang berhasil dihapus.");
        } else {
            System.out.println("Barang dengan ID tersebut tidak ditemukan.");
        }
    }

    // Mengedit barang berdasarkan ID
    public void editBarang(Scanner scanner) {
        System.out.println("\t  Edit Barang ");
        System.out.println("=================================");
        System.out.print("Masukkan ID barang yang ingin diubah: ");
        int id = Integer.parseInt(scanner.nextLine());
    
        // Mencari barang berdasarkan ID dan mengubah atributnya
        for (Barang barang : barangList) {
            if (barang.getId() == id) {
                System.out.print("Masukkan nama baru: ");
                barang.setNama(scanner.nextLine());
                System.out.print("Masukkan harga baru: ");
                barang.setHarga(Integer.parseInt(scanner.nextLine()));
                System.out.print("Masukkan stok baru: ");
                barang.setStok(Integer.parseInt(scanner.nextLine()));
                
                simpanBarangKeFile();  // Menyimpan perubahan ke file
                System.out.println("=================================");
                System.out.println("Barang berhasil diubah.");
                return;
            }
        }
        System.out.println("Barang dengan ID tersebut tidak ditemukan.");
    }

    // Menampilkan semua barang yang ada dalam daftar
    public void lihatBarang() {
        System.out.println("\n\t   === Daftar Barang ===");
        
        // Menampilkan informasi barang satu per satu
        for (Barang barang : barangList) {
            System.out.println(barang);
        }
    }

    // Menyimpan daftar barang ke dalam file
    void simpanBarangKeFile() {
        File file = new File("data/products.txt");
    
        try {
            // Membuat file jika belum ada
            if (!file.exists()) {
                file.getParentFile().mkdirs();  
                file.createNewFile();  
            }
    
            // Menulis data barang ke file
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                for (Barang barang : barangList) {
                    bw.write(barang.toStringFile()); // Menggunakan metode toStringFile untuk format file
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error menyimpan data barang."); // Menangani error saat menyimpan file
        }
    }
}
