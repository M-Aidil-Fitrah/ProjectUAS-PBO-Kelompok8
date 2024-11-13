public class Admin extends Akun {
    public Admin(String id) {
        super(id);
    }

    public void tambahBarang(Barang barang, ListBarang listBarang) {
        listBarang.tambahBarang(barang);
        System.out.println("Barang berhasil ditambahkan.");
    }

    public void hapusBarang(String idBarang, ListBarang listBarang) {
        boolean result = listBarang.hapusBarang(idBarang);
        if (result) {
            System.out.println("Barang berhasil dihapus.");
        } else {
            System.out.println("Barang tidak ditemukan.");
        }
    }

    public void editBarang(String idBarang, Barang barangBaru, ListBarang listBarang) {
        boolean result = listBarang.editBarang(idBarang, barangBaru);
        if (result) {
            System.out.println("Barang berhasil diedit.");
        } else {
            System.out.println("Barang tidak ditemukan.");
        }
    }

    public void terimaTransaksi(Transaksi transaksi) {
        System.out.println("Transaksi diterima untuk pelanggan: " + transaksi.getAkun().getId());
    }
}
