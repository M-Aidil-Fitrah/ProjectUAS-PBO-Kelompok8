import java.util.ArrayList;

public class ListBarang {
    private ArrayList<Barang> barangList;

    public ListBarang() {
        this.barangList = new ArrayList<>();
    }

    public void tambahBarang(Barang barang) {
        barangList.add(barang);
    }

    public boolean hapusBarang(String idBarang) {
        return barangList.removeIf(barang -> barang.getId().equals(idBarang));
    }

    public boolean editBarang(String idBarang, Barang barangBaru) {
        for (int i = 0; i < barangList.size(); i++) {
            if (barangList.get(i).getId().equals(idBarang)) {
                barangList.set(i, barangBaru);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Barang> getBarangList() {
        return barangList;
    }
}
