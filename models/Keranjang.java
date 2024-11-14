package models;

import java.util.ArrayList;

public class Keranjang {
    private ArrayList<Barang> barang;

    public Keranjang() {
        this.barang = new ArrayList<>();
    }

    public void tambahBarang(Barang item) {
        barang.add(item);
    }

    public ArrayList<Barang> getBarangList() {
        return barang;
    }
}
