import java.util.ArrayList;

public class Transaksi {
    private Customer akun;
    private ArrayList<Barang> barang;

    public Transaksi(Customer akun, ArrayList<Barang> barang) {
        this.akun = akun;
        this.barang = barang;
    }

    public Customer getAkun() {
        return akun;
    }

    public ArrayList<Barang> getBarang() {
        return barang;
    }
}
