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
        
    // Metode untuk menambahkan barang baru ke dalam koleksi listBarang
    public void tambahBarang(Barang barang) {
        listBarang.tambahBarang(barang);
    }
    // Metode untuk menghapus barang dari koleksi listBarang
    public void hapusBarang(Barang barang) {
        listBarang.hapusBarang(barang);
    }
    
    // Metode untuk memproses transaksi dengan menyetujui dan menambahkannya ke listTransaksi
    public void terimaTransaksi(Transaksi transaksi) {
        // Menyetujui transaksi
        transaksi.approve();
        // Menambahkan transaksi yang telah disetujui ke dalam listTransaksi
        listTransaksi.add(transaksi);
    }
    
    // Override metode manage untuk mengimplementasikan transaksi dan barang oleh admin
    @Override
    public void manage() {
        // Implementasi manajemen transaksi dan produk oleh Admin
    }
}
