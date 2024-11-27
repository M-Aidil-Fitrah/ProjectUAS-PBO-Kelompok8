package models;

// Kelas Barang, merepresentasikan data barang di program
public class Barang {
    private int id;             // ID barang
    private String nama;        // Nama barang
    private int harga;          // Harga barang
    private int stok;           // Stok barang
    private int jumlahCheckout; // Jumlah barang yang dibeli saat checkout

    // Konstruktor utama untuk inisialisasi barang
    public Barang(int id, String nama, int harga, int stok) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    // Konstruktor tambahan untuk barang yang sudah checkout
    public Barang(String nama, int jumlahCheckout) {
        this.nama = nama;
        this.jumlahCheckout = jumlahCheckout;
    }

    // Getter untuk jumlahCheckout
    public int getJumlahCheckout() {
        return jumlahCheckout;
    }

    // Setter untuk jumlahCheckout
    public void setJumlahCheckout(int jumlahCheckout) {
        this.jumlahCheckout = jumlahCheckout;
    }

    // Getter untuk ID barang
    public int getId() {
        return id;
    }

    // Getter untuk nama barang
    public String getNama() {
        return nama;
    }

    // Setter untuk nama barang
    public void setNama(String nama) {
        this.nama = nama;
    }

    // Getter untuk harga barang
    public int getHarga() {
        return harga;
    }

    // Setter untuk harga barang
    public void setHarga(int harga) {
        this.harga = harga;
    }

    // Getter untuk stok barang
    public int getStok() {
        return stok;
    }

    // Setter untuk stok barang
    public void setStok(int stok) {
        this.stok = stok;
    }

    // Representasi barang dalam format file
    public String toStringFile() {
        return id + "," + nama + "," + harga + "," + stok;
    }

    // Representasi barang dalam format string untuk ditampilkan
    @Override
    public String toString() {
        return "ID: " + id + ", Nama: " + nama + ", Harga: " + harga + ", Stok: " + stok;
    }
}
