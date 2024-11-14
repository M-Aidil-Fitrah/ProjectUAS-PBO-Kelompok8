package models;

import java.util.ArrayList;

public class ListBarang {
    private ArrayList<Barang> barang;

    public ListBarang() {
        this.barang = new ArrayList<>();
    }

    public void tambahBarang(Barang item) {
        barang.add(item);
    }

    public void hapusBarang(Barang item) {
        barang.remove(item);
    }

    // Menambahkan metode getter untuk mendapatkan daftar barang
    public ArrayList<Barang> getBarangList() {
        return barang;
    }

    // Menambahkan metode cariBarang
    public Barang cariBarang(String nama) {
        for (Barang item : barang) {
            if (item.getNama().equalsIgnoreCase(nama)) {
                return item;
            }
        }
        return null; // Jika barang tidak ditemukan
    }

    // Menambahkan metode tampilkanBarang
    public void tampilkanBarang() {
        if (barang.isEmpty()) {
            System.out.println("Tidak ada barang yang tersedia.");
        } else {
            System.out.println("Daftar Barang:");
            for (Barang item : barang) {
                System.out.println("- " + item.getNama() + " (Harga: " + item.getHarga() + ")");
            }
        }
    }
}
  