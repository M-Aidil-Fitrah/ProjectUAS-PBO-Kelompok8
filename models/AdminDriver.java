package models;

import java.util.ArrayList;

public class AdminDriver extends Driver {
    private ListBarang listBarang;
    private ArrayList<Transaksi> listTransaksi;

    public AdminDriver(Admin admin, ListBarang listBarang) {
        super(admin);
        this.listBarang = listBarang;
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
