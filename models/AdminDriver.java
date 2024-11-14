package models;

import java.util.ArrayList;

// Kelas AdminDriver mengimplementasikan fungsi pengelolaan transaksi dan barang oleh admin
public class AdminDriver extends Driver {
    // listBarang mewakili koleksi barang yang dikelola oleh admin
    private ListBarang listBarang;
    // listTransaksi adalah daftar transaksi yang diproses oleh admin
    private ArrayList<Transaksi> listTransaksi;

    
    // constructor AdminDriver menerima objek admin dan koleksi barang
    public AdminDriver(Admin admin, ListBarang listBarang) {
        // Memanggil konstruktor superclass (Driver) dengan parameter admin
        super(admin);
        // Menginisialisasi listBarang dengan koleksi barang yang diterima
        this.listBarang = listBarang;
        // Menginisialisasi listTransaksi sebagai daftar kosong
        this.listTransaksi = new ArrayList<>();
    }

    public void tambahBarang(Barang barang) {
        listBarang.tambahBarang(barang);
    }

    public void hapusBarang(Barang barang) {
        listBarang.hapusBarang(barang);
    }

    public void terimaTransaksi(Transaksi transaksi) {
        transaksi.approve();
        listTransaksi.add(transaksi);
    }

    @Override
    public void manage() {
        // Implementasi manajemen transaksi dan produk oleh Admin
    }
}
