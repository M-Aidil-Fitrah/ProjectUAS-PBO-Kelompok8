package models;

import java.util.*;

// Kelas Keranjang 
public class Keranjang {
    private Map<Barang, Integer> barangKeranjang;  // Map untuk menyimpan barang dan jumlahnya di keranjang

    // Konstruktor untuk inisialisasi keranjang dengan Map kosong
    public Keranjang() {
        this.barangKeranjang = new HashMap<>();
    }

    // Menambahkan barang ke keranjang dengan jumlah tertentu
    public void tambahBarang(Barang barang, int jumlah) {
        // Mengecek apakah barang sudah ada di keranjang
        if (barangKeranjang.containsKey(barang)) {
            // Jika sudah ada, tambahkan jumlah barang yang ada
            barangKeranjang.put(barang, barangKeranjang.get(barang) + jumlah);
        } else {
            // Jika belum ada, set jumlah checkout barang dan tambahkan ke keranjang
            barang.setJumlahCheckout(jumlah);
            barangKeranjang.put(barang, jumlah);
        }
    }

    // Menghitung total harga barang di keranjang
    public int totalHarga() {
        int total = 0;
        // Mengiterasi setiap barang dan jumlahnya untuk menghitung total harga
        for (Map.Entry<Barang, Integer> entry : barangKeranjang.entrySet()) {
            total += entry.getKey().getHarga() * entry.getValue(); // Mengalikan harga barang dengan jumlahnya
        }
        return total; // Mengembalikan total harga
    }

    // Mengecek apakah keranjang kosong
    public boolean isEmpty() {
        return barangKeranjang.isEmpty(); // Mengembalikan true jika keranjang kosong
    }

    // Getter untuk mendapatkan semua barang di keranjang
    public Map<Barang, Integer> getBarangKeranjang() {
        return barangKeranjang;
    }

    // Mengosongkan keranjang
    public void kosongkan() {
        barangKeranjang.clear(); // Menghapus semua barang dari keranjang
    }
}
