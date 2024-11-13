import java.util.ArrayList;

public class Keranjang {
    private ArrayList<Barang> barangList;

    public Keranjang() {
        this.barangList = new ArrayList<>();
    }

    public void tambahBarang(Barang barang) {
        barangList.add(barang);
    }

    public ArrayList<Barang> getBarangList() {
        return barangList;
    }

    public void tampilkanKeranjang() {
        System.out.println("Barang dalam keranjang:");
        for (Barang barang : barangList) {
            System.out.println(barang);
        }
    }
}
